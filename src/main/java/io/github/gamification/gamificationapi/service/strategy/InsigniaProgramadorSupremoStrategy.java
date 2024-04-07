package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.model.Dialogo;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class InsigniaProgramadorSupremoStrategy extends CheckInsigniaStrategy {

    @Autowired
    private RespostaRepository repository;
    private final long ID = 2;

    @Autowired
    private PersonagensProperties personagensProperties;

    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        return repository.findAllByIdUsuario(idUsuario)
                .stream()
                .filter(resposta -> resposta.isCorreto()).count() == getQuantidadeQuestoes();
    }

    private int getQuantidadeQuestoes(){
        return personagensProperties.getLista()
                .stream()
                .flatMap( p-> p.getLinhasDialogo().stream())
                .toList()
                .stream().filter(dialogo -> dialogo.getOpcoesResposta().size() >= 4).toList().size();
    }
}
