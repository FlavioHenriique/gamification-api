package io.github.gamification.gamificationapi.controller;

import io.github.gamification.gamificationapi.exception.PersonagemNotFoundException;
import io.github.gamification.gamificationapi.model.Personagem;
import io.github.gamification.gamificationapi.model.Questao;
import io.github.gamification.gamificationapi.model.Resposta;
import io.github.gamification.gamificationapi.request.SalvaRespostaRequest;
import io.github.gamification.gamificationapi.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService service;

    @GetMapping
    public ResponseEntity<Personagem> findAll(@RequestParam("idPersonagem") int id) throws PersonagemNotFoundException {
        return ResponseEntity.ok(service.findDadosPersonagem(id));
    }

    @PostMapping
    public ResponseEntity<Resposta> salvaRespostaQuestaoPersonagem(@RequestBody SalvaRespostaRequest request) throws Exception {
        return ResponseEntity.ok(service.salvaResposta(request));
    }
}
