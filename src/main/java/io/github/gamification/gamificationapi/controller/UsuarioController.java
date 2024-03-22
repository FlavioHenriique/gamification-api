package io.github.gamification.gamificationapi.controller;

import io.github.gamification.gamificationapi.exception.IncorrectPasswordException;
import io.github.gamification.gamificationapi.exception.UsuarioNotFoundException;
import io.github.gamification.gamificationapi.model.Anotacao;
import io.github.gamification.gamificationapi.model.Insignia;
import io.github.gamification.gamificationapi.model.Usuario;
import io.github.gamification.gamificationapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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

    @GetMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam("email") String email,
                                         @RequestParam("senha") String senha)
            throws IncorrectPasswordException, NoSuchAlgorithmException, UsuarioNotFoundException {
        return ResponseEntity.ok(service.login(email,senha));
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<Usuario>> ranking(){
        return ResponseEntity.ok(service.ranking());
    }

    @PostMapping("/adiconaInsignia")
    public ResponseEntity<Usuario> adicionaInsignia(@RequestParam("idUsuario") long idUsuario,
                                                    @RequestParam("idInsignia") long idInsignia) throws Exception {
        return ResponseEntity.ok(service.adicionaInsignia(idInsignia, idUsuario));
    }

    @PostMapping("/adicionaAnotacao")
    public ResponseEntity<Usuario> adicionaAnotacao(@RequestParam("idUsuario") long idUsuario,
                                                    @RequestParam("idAnotacao") long idAnotacao) throws Exception {
        return ResponseEntity.ok(service.adicionaAnotacao(idAnotacao, idUsuario));
    }

    @GetMapping("/anotacoes")
    public ResponseEntity<List<Anotacao>> obtemAnotacoes(@RequestParam("idUsuario") long idUsuario) throws Exception {
        return ResponseEntity.ok(service.obtemAnotacoes(idUsuario));
    }
}
