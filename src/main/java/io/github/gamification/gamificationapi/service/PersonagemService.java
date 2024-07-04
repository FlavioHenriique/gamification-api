package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.config.AnotacoesProperties;
import io.github.gamification.gamificationapi.config.InsigniaProperties;
import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.exception.PersonagemNotFoundException;
import io.github.gamification.gamificationapi.model.*;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import io.github.gamification.gamificationapi.request.SalvaRespostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagensProperties personagensProperties;

    @Autowired
    private AnotacoesProperties anotacoesProperties;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RespostaRepository repository;

    @Autowired
    private InsigniaService insigniaService;

    public Personagem findDadosPersonagem(int idPersonagem, long idUsuario) throws PersonagemNotFoundException {
        var lista = personagensProperties.getLista().stream().flatMap(item-> item.getLinhasDialogo().stream());
        int id = 1;
        for (int i = 0; i < personagensProperties.getLista().size(); i ++){
            Personagem personagem = personagensProperties.getLista().get(i);
            for (int k = 0; k < personagem.getLinhasDialogo().size(); k++){
                if (personagem.getLinhasDialogo().get(k).getOpcoesResposta().size() == 4){
                    personagem.getLinhasDialogo().get(k).setIdQuestao(id);
                    id ++;
                }
            }
        }
        Personagem personagem = personagensProperties.getLista()
                .stream()
                .filter(p -> p.getId() == idPersonagem)
                .findFirst()
                .orElseThrow(() -> new PersonagemNotFoundException("Personagem não encontrado"));

        personagem.setUltimaResposta(0);
        var respostasFeitas = repository.findAllByIdUsuarioAndPersonagem(idUsuario, idPersonagem);
        if (!respostasFeitas.isEmpty()){
            personagem.setUltimaResposta(respostasFeitas.get(0).getIdQuestao());
        }
        return personagem;
    }

    public RetornoInteracao salvaResposta(SalvaRespostaRequest request) throws Exception {
        var resposta = new Resposta();
        resposta.setIdPersonagem(request.getIdPersonagem());
        resposta.setCorreto(request.isCorreto());
        resposta.setMomento(LocalDateTime.now());
        resposta.setIdQuestao(request.getIdQuestao());
        resposta.setUsuario_id(request.getIdUsuario());
        repository.save(resposta);

        var usuario = usuarioService.find(request.getIdUsuario());
        if (request.isCorreto()){
            usuario.setPontuacao(usuario.getPontuacao() + 10);
            usuarioService.save(usuario);
        }
        var insigniasLiberadas = insigniaService.verificaInsigniasLiberadas(usuario.getIdsInsignias(), usuario.getId());

        if (!insigniasLiberadas.isEmpty()){
            List<Long> idInsignias = insigniasLiberadas.stream().map(ins -> ins.getId()).toList();
            if (usuario.getIdsInsignias() == null)
                usuario.setIdsInsignias(new ArrayList<>());

            usuario.getIdsInsignias().addAll(idInsignias);
            usuarioService.save(usuario);
        }
        adicionaAnotacao(usuario, request.getIdPersonagem());
        return RetornoInteracao.builder()
                .usuario(usuario)
                .insigniasLiberadas(insigniasLiberadas).build();
    }

    private void adicionaAnotacao(Usuario usuario, int idPersonagem) throws Exception {
        Anotacao anotacao = anotacoesProperties
                .getLista()
                .stream()
                .filter(anot-> anot.getId() == idPersonagem).findFirst()
                .orElseThrow();
        if (!usuario.getIdsAnotacoes().contains(anotacao.getId())){
            usuario.getIdsAnotacoes().add(anotacao.getId());
            usuario.getAnotacoes().add(anotacao);
            usuarioService.save(usuario);
        }
    }
}