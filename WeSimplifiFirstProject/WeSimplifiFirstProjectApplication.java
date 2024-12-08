package com.Wesimplifi.WeSimplifiFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
//@EntityScan(basePackages = "com.Wesimplifi.WeSimplifiFirstProject.entity")
public class WeSimplifiFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeSimplifiFirstProjectApplication.class, args);
	}

}
