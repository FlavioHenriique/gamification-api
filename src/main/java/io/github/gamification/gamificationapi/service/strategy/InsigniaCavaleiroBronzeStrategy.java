package io.github.gamification.gamificationapi.service.strategy;

import io.github.gamification.gamificationapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsigniaCavaleiroBronzeStrategy extends CheckInsigniaStrategy {

    @Autowired
    private UsuarioService service;
    private final long ID = 4;


    @Override
    long getId() {
        return this.ID;
    }

    @Override
    public boolean checkCondition(long idUsuario) {
        return !service.ranking()
                .stream()
                .limit(3)
                .filter(usuario -> usuario.getId() == idUsuario)
                .toList().isEmpty(); // se a lista não estiver vazia, é porque o usuário está em alguma posição do top 3
    }
}
