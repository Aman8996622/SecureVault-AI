package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		// Try to find .env in multiple common locations
		Dotenv dotenv = Dotenv.configure()
				.directory("./secure_vault_ai") // Try sub-directory first (root project start)
				.ignoreIfMissing()
				.load();

		if (dotenv.get("SPRING_DATASOURCE_URL") == null) {
			// Try current directory
			dotenv = Dotenv.configure()
					.directory("./")
					.ignoreIfMissing()
					.load();
		}

		System.out.println("Checking for variables in .env file...");
		dotenv.entries().forEach(entry -> {
			// Only set if not already set by system environment and exists in our dotenv
			System.setProperty(entry.getKey(), entry.getValue());
			System.out.println("Property Set: " + entry.getKey() + "=" + entry.getValue());
		});

		SpringApplication.run(DemoApplication.class, args);
	}

}
