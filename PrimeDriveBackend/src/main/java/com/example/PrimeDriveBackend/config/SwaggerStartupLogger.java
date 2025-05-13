package com.example.PrimeDriveBackend.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Logs the Swagger UI URL to the console when the application is ready.
 */
@Component
public class SwaggerStartupLogger {

    /**
     * Event listener that prints the Swagger UI URL after the application has
     * started.
     *
     * @param event the application ready event
     */
    @EventListener(ApplicationReadyEvent.class)
    public void logSwaggerUrl() {
        System.out.println("📘 Swagger UI available at: http://localhost:8080/swagger-ui/index.html");
    }
}