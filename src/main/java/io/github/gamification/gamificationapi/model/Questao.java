package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Questao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String enunciado;
    @ElementCollection
    private List<Alternativa> alternativas;

    private boolean acertou;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public static  class Alternativa implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String texto;
        private boolean correta;
    }

    public static final List<Questao> QUESTOES = Arrays.asList(new Questao[]{
            Questao.builder()
                    .id(1)
                    .enunciado("Teste questão 1")
                    .alternativas(Arrays.asList(new Questao.Alternativa[]{
                            Questao.Alternativa.builder()
                                    .id(1)
                                    .texto("Resposta 1")
                                    .correta(Boolean.FALSE)
                                    .build(),
                            Questao.Alternativa.builder()
                                    .id(2)
                                    .texto("Resposta 2")
                                    .correta(Boolean.FALSE)
                                    .build(),
                            Questao.Alternativa.builder()
                                    .id(3)
                                    .texto("Resposta 3")
                                    .correta(Boolean.FALSE)
                                    .build(),
                            Questao.Alternativa.builder()
                                    .id(4)
                                    .texto("Resposta 4")
                                    .correta(Boolean.TRUE)
                                    .build(),
                    }))
                    .build(),
            Questao.builder()
                    .id(2)
                    .enunciado("Teste questão 2")
                    .alternativas(Arrays.asList(new Questao.Alternativa[]{
                            Questao.Alternativa.builder()
                                    .id(1)
                                    .texto("Resposta 1")
                                    .correta(Boolean.FALSE)
                                    .build(),
                            Questao.Alternativa.builder()
                                    .id(2)
                                    .texto("Resposta 2")
                                    .correta(Boolean.FALSE)
                                    .build(),
                            Questao.Alternativa.builder()
                                    .id(3)
                                    .texto("Resposta 3")
                                    .correta(Boolean.TRUE)
                                    .build(),
                            Questao.Alternativa.builder()
                                    .id(4)
                                    .texto("Resposta 4")
                                    .correta(Boolean.FALSE)
                                    .build(),
                    }))
                    .build()
    });


}


