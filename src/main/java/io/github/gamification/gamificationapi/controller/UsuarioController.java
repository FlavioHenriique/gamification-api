package io.github.gamification.gamificationapi.controller;

import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping()
    public ResponseEntity<Usuario> salva(@RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(service.save(usuario));
    }

    @GetMapping
    public ResponseEntity<Usuario> getUsuario(@RequestParam("id") long id) throws Exception {
        return ResponseEntity.ok(service.find(id));
    }
}
