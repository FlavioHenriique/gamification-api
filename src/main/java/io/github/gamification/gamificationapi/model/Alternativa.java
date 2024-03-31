package io.github.gamification.gamificationapi.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alternativa implements Serializable {
    private long id;
    private String texto;
    private boolean correto;
}