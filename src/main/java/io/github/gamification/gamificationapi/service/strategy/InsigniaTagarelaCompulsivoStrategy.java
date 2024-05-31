package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.model.Resposta;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaTagarelaCompulsivoStrategy extends CheckInsigniaStrategy {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private PersonagensProperties personagensProperties;

    private final long ID = 10;

    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        return repository.findAllByIdUsuario(idUsuario)
                .stream()
                .map(Resposta::getIdPersonagem)
                .distinct()
                .toList().size() == getQtdPersonagens();
    }

    private int getQtdPersonagens(){
        return personagensProperties.getLista().size();
    }
}
