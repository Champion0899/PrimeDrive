package com.example.PrimeDriveBackend.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SwaggerStartupLogger {

    @EventListener(ApplicationReadyEvent.class)
    public void logSwaggerUrl() {
        System.out.println("📘 Swagger UI verfügbar unter: http://localhost:8080/swagger-ui/index.html");
    }
}