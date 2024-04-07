package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaIniciantePromissorStrategy extends CheckInsigniaStrategy {

    @Autowired
    private RespostaRepository repository;

    private final long ID = 2;

    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        return repository.findFirst3(idUsuario)
                .stream()
                .filter(resposta -> resposta.isCorreto()).count() == 3;
    }
}
