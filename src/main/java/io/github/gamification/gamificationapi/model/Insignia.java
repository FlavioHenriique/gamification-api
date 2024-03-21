package io.github.gamification.gamificationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean conquistada;
    @Transient
    private int percentualUsuarios;

    @Transient
    public static final List<Insignia> INSIGNIAS = Arrays.asList(new Insignia[]{
        
        // NUMERO DE QUESTÕES    
        Insignia.builder()
                    .id(1l)
                    .nome("Iniciante promissor")
                    .descricao("Acerte as 3 primeiras questões")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(2l)
                    .nome("Programador supremo")
                    .descricao("Acerte todas as questões")
                    .imagem("imagem.png")
                    .build(),
        
        // RANKING    
        Insignia.builder()
                    .id(3l)
                    .nome("Monitor de algoritmos")
                    .descricao("Lidere o ranking em algum momento")
                    .imagem("imagem.png")
                    .build(),
        Insignia.builder()
                    .id(4l)
                    .nome("Cavaleiro de bronze")
                    .descricao("Esteja no TOP 3  do ranking em algum momento")
                    .imagem("imagem.png")
                    .build(),
        Insignia.builder()
                    .id(5l)
                    .nome("Metade da jornada")
                    .descricao("Consiga 50 pontos")
                    .imagem("imagem.png")
                    .build(),
        
        // CADERNO DE ANOTAÇÕES    
        Insignia.builder()
                    .id(6l)
                    .nome("Enciclopédia viva")
                    .descricao("Complete o caderno de anotações")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(7l)
                    .nome("Diário do programador")
                    .descricao("Adquira sua primeira anotação")
                    .imagem("imagem.png")
                    .build(),
            Insignia.builder()
                    .id(8l)
                    .nome("Guardião do saber")
                    .descricao("Preencha 4 anotações")
                    .imagem("imagem.png")
                    .build(),

        // CONVERSA COM PERSONAGENS
        Insignia.builder()
                    .id(9l)
                    .nome("Saindo da caverna")
                    .descricao("Complete sua primeira interação com algum personagem")
                    .imagem("imagem.png")
                    .build(),
        Insignia.builder()
                    .id(10l)
                    .nome("Tagarela compulsivo")
                    .descricao("Converse com todos os personagens")
                    .imagem("imagem.png")
                    .build(),

        Insignia.builder()
                    .id(11l)
                    .nome("A Skynet é real")
                    .descricao("Converse com Ian, a IA")
                    .imagem("imagem.png")
                    .build(),

        Insignia.builder()
                    .id(12l)
                    .nome("Rei da platina")
                    .descricao("Libere todas as insígnias")
                    .imagem("imagem.png")
                    .build()  
    });
}
