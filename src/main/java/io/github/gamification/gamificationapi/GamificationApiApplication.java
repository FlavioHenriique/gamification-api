package io.github.gamification.gamificationapi;

import io.github.gamification.gamificationapi.config.QuestoesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(QuestoesConfig.class)

public class GamificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamificationApiApplication.class, args);
	}

}
