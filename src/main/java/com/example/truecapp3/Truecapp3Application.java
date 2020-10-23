package com.example.truecapp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Truecapp3Application {

	public static void main(String[] args) {
		SpringApplication.run(Truecapp3Application.class, args);
	}

}
