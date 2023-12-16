package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario implements Serializable {

    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String senha;
    private String usuario;
    private long pontuacao;
    @ElementCollection
    private List<Insignia> insigniasConquistadas;
    @ElementCollection
    private List<Questao> questoesRespondidas;
    @ElementCollection
    private List<Anotacao> anotacoes;
}
