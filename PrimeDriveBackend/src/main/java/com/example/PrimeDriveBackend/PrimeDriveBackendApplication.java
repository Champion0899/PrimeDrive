package com.example.PrimeDriveBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the PrimeDrive backend application.
 *
 * This class bootstraps the Spring Boot application and serves as the entry
 * point
 * for the entire backend service.
 *
 * Author: Fatlum Epiroti & Jamie Schüpbach
 * Version: 1.0
 * Date: 2025-06-03
 */
@SpringBootApplication
public class PrimeDriveBackendApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 *
	 * @param args command-line arguments passed during application startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(PrimeDriveBackendApplication.class, args);
	}

}
