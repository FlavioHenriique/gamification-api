package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.config.PersonagensProperties;
import io.github.gamification.gamificationapi.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaSkynetStrategy extends CheckInsigniaStrategy {

    @Autowired
    private RespostaRepository repository;
    private final long ID = 11;

    @Autowired
    private PersonagensProperties personagensProperties;

    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        var idIan = getIdIan();
        return repository.findAllByIdUsuario(idUsuario)
                .stream()
                .filter(resposta -> resposta.getIdPersonagem() == idIan).count() > 0;
    }

    private int getIdIan() {
        return personagensProperties.getLista().stream()
                .filter(personagem -> personagem.getNome().equals("Ian"))
                .findFirst().get().getId();
    }
}
