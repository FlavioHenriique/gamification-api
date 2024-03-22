package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.exception.PersonagemNotFoundException;
import io.github.gamification.gamificationapi.model.Personagem;
import io.github.gamification.gamificationapi.model.Questao;
import io.github.gamification.gamificationapi.model.Resposta;
import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import io.github.gamification.gamificationapi.request.SalvaRespostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    @Autowired
    private PersonagensProperties personagensProperties;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RespostaRepository repository;

    public Personagem findDadosPersonagem(int idPersonagem) throws PersonagemNotFoundException {
        return personagensProperties.getLista()
                .stream()
                .filter(p -> p.getId() == idPersonagem)
                .findFirst()
                .orElseThrow(() -> new PersonagemNotFoundException("Personagem não encontrado"));
    }

    public Resposta salvaResposta(SalvaRespostaRequest request) throws Exception {
        var resposta = new Resposta();
        resposta.setIdPersonagem(request.getIdPersonagem());
        resposta.setCorreto(request.isCorreto());

        resposta.setUsuario(usuarioService.find(request.getIdUsuario()));
        return repository.save(resposta);
    }
}