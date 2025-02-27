package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.config.AnotacoesProperties;
import io.github.gamification.gamificationapi.config.InsigniaProperties;
import io.github.gamification.gamificationapi.exception.IncorrectPasswordException;
import io.github.gamification.gamificationapi.exception.UsuarioExistsException;
import io.github.gamification.gamificationapi.exception.UsuarioNotFoundException;
import io.github.gamification.gamificationapi.model.Anotacao;
import io.github.gamification.gamificationapi.model.Insignia;
import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.repository.InsigniaRepository;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import io.github.gamification.gamificationapi.utils.MD5Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private InsigniaRepository insigniaRepository;

    @Autowired
    private AnotacoesProperties anotacoesProperties;

    @Autowired
    private InsigniaProperties insigniaProperties;

    @Autowired
    private RespostaRepository respostaRepository;

    public Usuario save(Usuario usuario) throws Exception {
        if (usuario.getId() <= 0){
            var optional = repository.findByEmail(usuario.getEmail());
            if (optional.isPresent()){
                throw new UsuarioExistsException("Email já existe");
            }
            optional = repository.findByUsuario(usuario.getUsuario());
            if (optional.isPresent()){
                throw new UsuarioExistsException("Usuário já existe");
            }
            usuario.setSenha(MD5Converter.convertToMd5(usuario.getSenha()));
        }
        return repository.save(usuario);
    }

    public Usuario find(long id) throws Exception {
        return repository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }
    public Usuario login(String email, String senha) throws UsuarioNotFoundException, IncorrectPasswordException, NoSuchAlgorithmException {
        var optional = repository.findByEmail(email);
        Usuario usuario = null;
        if (!optional.isPresent()){
            usuario = repository.findByUsuario(email).orElseThrow(UsuarioNotFoundException::new);
        }else {
            usuario = optional.get();
        }
        if (usuario.getIdsInsignias() == null)
            usuario.setIdsInsignias(new ArrayList<>());

        if (!MD5Converter.convertToMd5(senha).equals(usuario.getSenha())){
            throw new IncorrectPasswordException("senha errada");
        }

        usuario = preencheInformacoesUsuario(usuario);
        return usuario;
    }

    public Usuario preencheInformacoesUsuario(Usuario usuario){
        preencheInsignias(usuario);
        preenchePontuacao(usuario);
        preencheRespostas(usuario);
        preencheAnotacoes(usuario);
        return usuario;
    }

    private void preencheAnotacoes(Usuario usuario){
        List<Anotacao> list = anotacoesProperties
                .getLista()
                .stream()
                .filter(anot -> usuario.getIdsAnotacoes().contains(anot.getId())).toList();

        usuario.setAnotacoes(list);
    }
    private void preencheRespostas(Usuario usuario) {
        usuario.setRespostas(respostaRepository.findAllByIdUsuario(usuario.getId()));
    }

    private void preenchePontuacao(Usuario usuario) {
        List<Usuario> ranking = ranking();
        for (int i = 0; i < ranking.size(); i ++){
            if (usuario.getId() == ranking.get(i).getId()){
                usuario.setPosicaoRanking(i+1);
                break;
            }
        }
    }

    private void preencheInsignias(Usuario usuario){
        var insignias = insigniaProperties.getLista();

        int totalUsuarios = repository.findAll().size();
        insignias.forEach(i-> {
            if (usuario.getIdsInsignias().contains(i.getId())){
                i.setConquistada(true);
                i.setPercentualUsuarios(percentualDeUsuarios(i.getId(), totalUsuarios));
            }
        });
        usuario.setInsignias(insignias);
    }

    private int percentualDeUsuarios(long idInsignia, int totalUsuarios){
        return (insigniaRepository.contaPercentualUsuarios(idInsignia)*100)/ totalUsuarios;
    }

    public List<Usuario> ranking(){
        return repository.findAllOrderByPontuacaoDesc();
    }

    public Usuario adicionaInsignia(long idInsignia, long id) throws Exception {
        if (insigniaRepository.findAll().size() != insigniaProperties.getLista().size()){
            insigniaRepository.saveAll(insigniaProperties.getLista());
        }
        var insignia = insigniaProperties.getLista().stream().filter(i-> i.getId() == idInsignia).findFirst().orElseThrow();
        var usuario = find(id);

        if (!usuario.getInsignias()
                .stream()
                .filter(i-> i.getId() == idInsignia)
                .collect(Collectors.toList()).isEmpty())
            return usuario;

        usuario.getInsignias().add(insignia);
        repository.save(usuario);
        preencheInsignias(usuario);
        preenchePontuacao(usuario);
        return usuario;
    }

    private Insignia consultaInsignia(long id){
        return insigniaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Usuario adicionaAnotacao(long idAnotacao, long idUsuario) throws Exception {

        var usuario = find(idUsuario);
        if (usuario.getIdsAnotacoes() == null) {
            usuario.setIdsAnotacoes(new ArrayList<>());
        }

        if (usuario.getIdsAnotacoes().contains(idAnotacao)) {
            return usuario;
        }
        usuario.getIdsAnotacoes().add(idAnotacao);
        return repository.save(usuario);
    }

    public List<Anotacao> obtemAnotacoes(long idUsuario) throws Exception {
        var usuario = find(idUsuario);
        return anotacoesProperties.getLista().stream()
                .filter(a -> usuario.getIdsAnotacoes().contains(a.getId()))
                .collect(Collectors.toList());
    }
}
