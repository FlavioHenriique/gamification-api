package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
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
    @Transient
    private long posicaoRanking;
    @Column(nullable = true)
    private boolean visualizouPrimeiraPagina;
    @ElementCollection
    private List<Insignia> insigniasConquistadas;
    @ElementCollection
    private List<Questao> questoesRespondidas;
    @ElementCollection
    private List<Anotacao> anotacoes;
}
