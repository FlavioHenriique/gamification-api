package io.github.gamification.gamificationapi.service;

import io.github.gamification.gamificationapi.model.Questao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestaoService {

    public List<Questao> findQuestoes(){
        return Questao.QUESTOES;
    }
}
