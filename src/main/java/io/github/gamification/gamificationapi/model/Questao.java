package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Arrays;
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


