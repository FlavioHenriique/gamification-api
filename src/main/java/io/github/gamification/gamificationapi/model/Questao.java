package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Questao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String enunciado;
    @ElementCollection
    private List<Alternativa> alternativas;

    private boolean acertou;

    @Getter
    @Setter
    @NoArgsConstructor
    @Entity
    public class Alternativa implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String texto;
        private boolean correta;
    }
}


