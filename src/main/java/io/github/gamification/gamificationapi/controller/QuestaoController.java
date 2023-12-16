package io.github.gamification.gamificationapi.controller;

import io.github.gamification.gamificationapi.model.Questao;
import io.github.gamification.gamificationapi.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questao")
public class QuestaoController {

    @Autowired
    private QuestaoService service;

    @GetMapping
    public ResponseEntity<List<Questao>> findAll(){
        return ResponseEntity.ok(service.findQuestoes());
    }
}
