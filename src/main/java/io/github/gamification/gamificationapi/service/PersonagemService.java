package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.config.InsigniaProperties;
import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.exception.PersonagemNotFoundException;
import io.github.gamification.gamificationapi.model.Dialogo;
import io.github.gamification.gamificationapi.model.Personagem;
import io.github.gamification.gamificationapi.model.Resposta;
import io.github.gamification.gamificationapi.model.RetornoInteracao;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import io.github.gamification.gamificationapi.request.SalvaRespostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonagemService {

    @Autowired
    private PersonagensProperties personagensProperties;
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

        var usuario = usuarioService.find(request.getIdUsuario()) ;
        var insignias = usuario.getInsignias().stream().map(i-> i.getId()).toList();
        var insigniasLiberadas = insigniaService.verificaInsigniasLiberadas(insignias, usuario.getId());
        insigniasLiberadas.forEach(i-> {
            try {
                usuarioService.adicionaInsignia(i.getId(), usuario.getId());
            } catch (Exception e) {
                System.out.println("Erro liberando insignia: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });
        return RetornoInteracao.builder().insigniasLiberadas(insigniasLiberadas).build();
    }
}