package io.github.gamification.gamificationapi.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RetornoInteracao {
    private List<Insignia> insigniasLiberadas;
    private List<Anotacao> anotacoesLiberadas;
    private Usuario usuario;

}
