package io.github.gamification.gamificationapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SalvaRespostaRequest {

    private long idUsuario;
    private int idPersonagem;
    private int idQuestao;
    private boolean correto;
}
