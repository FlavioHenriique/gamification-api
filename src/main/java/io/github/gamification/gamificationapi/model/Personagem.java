package io.github.gamification.gamificationapi.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Personagem {
    private int id;
    private String nome;
    private List<Dialogo> linhasDialogo;

}
