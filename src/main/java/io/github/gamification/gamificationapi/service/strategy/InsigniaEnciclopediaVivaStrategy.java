package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.AnotacoesProperties;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaEnciclopediaVivaStrategy extends CheckInsigniaStrategy {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AnotacoesProperties properties;
    private final long ID = 6;


    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
       return repository.findById(idUsuario).get().getIdsAnotacoes().size() == getQtdAnotacoes();
    }

    private int getQtdAnotacoes(){
        return properties.getLista().size();
    }
}
