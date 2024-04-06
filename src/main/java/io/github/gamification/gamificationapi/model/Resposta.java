package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long usuario_id;
    private int idPersonagem;
    private int idQuestao;
    private boolean correto;
    private LocalDateTime momento;
}
