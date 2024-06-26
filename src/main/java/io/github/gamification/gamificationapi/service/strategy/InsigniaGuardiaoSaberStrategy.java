package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.AnotacoesProperties;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsigniaGuardiaoSaberStrategy extends CheckInsigniaStrategy {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AnotacoesProperties properties;
    private final long ID = 8;


    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        List<Long> idsAnotacoes = repository.findById(idUsuario).get().getIdsAnotacoes();
        if (idsAnotacoes != null)
            return idsAnotacoes.size() >= 4;
        return false;
    }

}
