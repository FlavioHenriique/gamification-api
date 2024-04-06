package io.github.gamification.gamificationapi.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Questao implements Serializable {

    private long id;
    private String enunciado;
    private List<Alternativa> alternativas;
    private boolean acertou;
    private List<Dialogo> linhasDialogo;
}


