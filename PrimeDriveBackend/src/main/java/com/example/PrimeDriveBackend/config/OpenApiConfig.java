package com.example.PrimeDriveBackend.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration class for the PrimeDrive application.
 *
 * This class sets up the Swagger/OpenAPI documentation with title, version,
 * and description metadata for the PrimeDrive REST API.
 *
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures and returns the OpenAPI documentation for the application.
     *
     * Sets metadata such as title, version, and description for Swagger UI.
     *
     * @return an OpenAPI instance containing API documentation settings
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PrimeDrive API")
                        .version("1.0")
                        .description("Documentation of PrimeDrive REST-API"));
    }
}