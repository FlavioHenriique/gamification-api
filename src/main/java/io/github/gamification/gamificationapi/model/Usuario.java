package io.github.gamification.gamificationapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String usuario;

    private long pontuacao;

    private List<Insignia> insigniasConquistadas;
    private List<Questao> questoesRespondidas;

    private List<Anotacao> anotacoes;
}
