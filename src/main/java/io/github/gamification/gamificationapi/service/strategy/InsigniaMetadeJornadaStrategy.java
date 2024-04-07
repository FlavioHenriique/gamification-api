package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import io.github.gamification.gamificationapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaMetadeJornadaStrategy extends CheckInsigniaStrategy {

    @Autowired
    private UsuarioRepository repository;
    private final long ID = 5;


    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
       return repository.findById(idUsuario).get().getPontuacao() >= 50;
    }
}
