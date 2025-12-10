package com.example.PrimeDriveBackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Test class for verifying Spring Boot context loading for the PrimeDrive
 * backend application.
 *
 * Ensures that the application context starts up correctly without any
 * configuration errors.
 *
 * Author: Fatlum Epiroti & Jamie Schüpbach
 * Version: 1.0
 * Date: 2025-06-03
 */
@SpringBootTest
@ActiveProfiles("test")
class PrimeDriveBackendApplicationTests {

	@Test
	/**
	 * Verifies that the Spring application context loads successfully.
	 */
	void contextLoads() {
	}

}
