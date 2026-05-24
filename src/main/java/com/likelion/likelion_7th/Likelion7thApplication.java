package com.likelion.likelion_7th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Likelion7thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Likelion7thApplication.class, args);
	}

}
