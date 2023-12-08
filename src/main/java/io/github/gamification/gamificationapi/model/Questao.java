package io.github.gamification.gamificationapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Questao {

    private long id;
    private String enunciado;
    private List<Alternativa> alternativas;

    private boolean acertou;

    @Getter
    @Setter
    @NoArgsConstructor
    public class Alternativa{
        private String texto;
        private boolean correta;
    }
}


