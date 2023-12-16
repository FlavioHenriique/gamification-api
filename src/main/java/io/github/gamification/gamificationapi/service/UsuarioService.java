package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.exception.IncorrectPasswordException;
import io.github.gamification.gamificationapi.exception.UsuarioExistsException;
import io.github.gamification.gamificationapi.exception.UsuarioNotFoundException;
import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.repository.UsuarioRepository;
import io.github.gamification.gamificationapi.utils.MD5Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario save(Usuario usuario) throws Exception {
        var optional = repository.findByEmail(usuario.getEmail());
        if (optional.isPresent()){
            throw new UsuarioExistsException("Usuário já existe");
        }
        usuario.setSenha(MD5Converter.convertToMd5(usuario.getSenha()));
        return repository.save(usuario);
    }

    public Usuario find(long id) throws Exception {
        return repository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }
    public Usuario login(String email, String senha) throws UsuarioNotFoundException, IncorrectPasswordException, NoSuchAlgorithmException {
        var usuario = repository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        if (!MD5Converter.convertToMd5(senha).equals(usuario.getSenha())){
            throw new IncorrectPasswordException("senha errada");
        }
        return usuario;
    }
}
