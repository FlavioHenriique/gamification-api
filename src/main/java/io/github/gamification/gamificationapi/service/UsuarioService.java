package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.exception.UsuarioExistsException;
import io.github.gamification.gamificationapi.exception.UsuarioNotFoundException;
import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario save(Usuario usuario) throws Exception {
        var optional = repository.findByEmail(usuario.getEmail());
        if (optional.isPresent()){
            throw new UsuarioExistsException("Usuário já existe");
        }

        return repository.save(usuario);
    }

    public Usuario find(long id) throws Exception {
        return repository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }
}
