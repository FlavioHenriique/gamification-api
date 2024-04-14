package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.AnotacoesProperties;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsigniaDiarioProgramadorStrategy extends CheckInsigniaStrategy {

    @Autowired
    private UsuarioRepository repository;

    private final long ID = 7;


    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        List<Long> idsAnotacoes = repository.findById(idUsuario).get().getIdsAnotacoes();
       if (idsAnotacoes != null)
           return !idsAnotacoes.isEmpty();
       return false;
    }

}
