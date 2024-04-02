package br.com.fiap.economed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EconomedApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconomedApplication.class, args);
	}

}
