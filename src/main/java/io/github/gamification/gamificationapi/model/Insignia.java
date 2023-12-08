package io.github.gamification.gamificationapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Insignia {

    private long id;
    private String nome;
    private String descricao;
    private String imagem;
}
