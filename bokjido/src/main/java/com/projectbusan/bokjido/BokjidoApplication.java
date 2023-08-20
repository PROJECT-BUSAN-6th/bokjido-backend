package com.projectbusan.bokjido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BokjidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BokjidoApplication.class, args);
	}

}
