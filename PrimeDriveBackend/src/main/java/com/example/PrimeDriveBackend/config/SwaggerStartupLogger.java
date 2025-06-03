package com.example.PrimeDriveBackend.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Logs the Swagger UI URL to the console when the Spring Boot application has fully started.
 *
 * This component listens for the ApplicationReadyEvent and outputs the Swagger UI access URL,
 * making it easy for developers to quickly access API documentation during development.
 *
 * @author Fatlum
 * @version 1.0
 * @since 2025-06-03
 */
@Component
public class SwaggerStartupLogger {

    /**
     * Event listener that logs the Swagger UI URL after application startup.
     *
     * @param event the event fired when the application is ready
     */
    @EventListener(ApplicationReadyEvent.class)
    public void logSwaggerUrl() {
        System.out.println("📘 Swagger UI available at: https://localhost:8443/swagger-ui/index.html");
    }
}