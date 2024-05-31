package io.github.gamification.gamificationapi.model;

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
    private String questao;
    private int idQuestao;
}
