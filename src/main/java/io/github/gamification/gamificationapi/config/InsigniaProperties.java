package io.github.gamification.gamificationapi.config;

import io.github.gamification.gamificationapi.model.Insignia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "insignias")
@Configuration
@Getter
@Setter
@NoArgsConstructor
public class InsigniaProperties {

    private List<Insignia> lista;
}
