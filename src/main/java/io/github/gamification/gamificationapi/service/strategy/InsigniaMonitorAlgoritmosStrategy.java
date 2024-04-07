package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import io.github.gamification.gamificationapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaMonitorAlgoritmosStrategy extends CheckInsigniaStrategy {

    @Autowired
    private UsuarioService service;
    private final long ID = 3;


    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        return service.ranking().stream().findFirst().get().getId() == idUsuario;
    }
}
