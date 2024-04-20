package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.InsigniaProperties;
import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.model.Resposta;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaReiDaPlatinaStrategy extends CheckInsigniaStrategy {

    @Autowired
    private InsigniaProperties properties;

    @Autowired
    private UsuarioRepository repository;

    private final long ID = 12;

    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        return repository.findById(idUsuario).get().getInsignias().size() == getQtdInsignias();
    }

    private int getQtdInsignias() {
        return properties.getLista().size();
    }
}
