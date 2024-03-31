package io.github.gamification.gamificationapi.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dialogo {
    private String mensagem;
    private List<Alternativa> opcoesResposta;
    private int imagem;
    private boolean ultima;
}
