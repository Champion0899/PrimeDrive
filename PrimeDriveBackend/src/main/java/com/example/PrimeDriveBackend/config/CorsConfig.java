package com.example.PrimeDriveBackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * CORS configuration class for the PrimeDrive application.
 * 
 * This class defines the allowed origins, HTTP methods, and headers for
 * cross-origin requests
 * in order to enable frontend-backend communication between Angular (on
 * localhost:4200)
 * and the Spring Boot backend.
 * 
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    /**
     * Configures CORS mappings for the entire application.
     * 
     * Allows requests from the specified frontend origin and permits all HTTP
     * methods and headers.
     *
     * @param registry the CorsRegistry used to register cross-origin mappings
     */
    @Override
    public void addCorsMappings(@org.springframework.lang.NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
