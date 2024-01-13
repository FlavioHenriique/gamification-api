package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.exception.IncorrectPasswordException;
import io.github.gamification.gamificationapi.exception.UsuarioExistsException;
import io.github.gamification.gamificationapi.exception.UsuarioNotFoundException;
import io.github.gamification.gamificationapi.model.Insignia;
import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.repository.InsigniaRepository;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import io.github.gamification.gamificationapi.utils.MD5Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private InsigniaRepository insigniaRepository;

    public Usuario save(Usuario usuario) throws Exception {
        if (usuario.getId() <= 0){
            var optional = repository.findByEmail(usuario.getEmail());
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
        var usuario = repository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        if (!MD5Converter.convertToMd5(senha).equals(usuario.getSenha())){
            throw new IncorrectPasswordException("senha errada");
        }
        return usuario;
    }

    public List<Usuario> ranking(){
        return repository.findAllOrderByPontuacaoDesc();
    }

    public Usuario adicionaInsignia(long idInsignia, long id) throws Exception {
        if (insigniaRepository.findAll().isEmpty()){
            insigniaRepository.saveAll(Insignia.INSIGNIAS);
        }

        var usuario = find(id);
        var insignia = consultaInsignia(idInsignia);

        if (!usuario.getInsigniasConquistadas()
                .stream()
                .filter(i-> i.getId() == idInsignia)
                .collect(Collectors.toList()).isEmpty())
            return usuario;

        usuario.getInsigniasConquistadas().add(insignia);
        return repository.save(usuario);
    }

    private Insignia consultaInsignia(long id){
        return insigniaRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
