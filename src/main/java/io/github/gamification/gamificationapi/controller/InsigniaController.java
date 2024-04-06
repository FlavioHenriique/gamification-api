package io.github.gamification.gamificationapi.controller;

import io.github.gamification.gamificationapi.model.Insignia;
import io.github.gamification.gamificationapi.service.InsigniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/insignia")
public class InsigniaController {

    @Autowired
    private InsigniaService insigniaService;

    @GetMapping
    public ResponseEntity<List<Insignia>> getAll(){
        return ResponseEntity.ok(insigniaService.getListaInsignias());
    }
}
