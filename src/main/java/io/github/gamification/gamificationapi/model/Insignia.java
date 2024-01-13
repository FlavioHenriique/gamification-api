package io.github.gamification.gamificationapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Insignia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String descricao;
    private String imagem;

    @Transient
    public static final List<Insignia> INSIGNIAS = Arrays.asList(new Insignia[]{
            Insignia.builder()
                    .id(1l)
                    .nome("insignia 1")
                    .descricao("descricao")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(2l)
                    .nome("insignia 2")
                    .descricao("descricao")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(3l)
                    .nome("insignia 3")
                    .descricao("descricao")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(4l)
                    .nome("insignia 4")
                    .descricao("descricao")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(5l)
                    .nome("insignia 5")
                    .descricao("descricao")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(6l)
                    .nome("insignia 6")
                    .descricao("descricao")
                    .imagem("imagem.png")
                    .build()
    });
}
