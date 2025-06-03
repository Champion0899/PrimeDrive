package com.example.PrimeDriveBackend.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@Configuration
@SecurityScheme(name = "bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
/**
 * OpenAPI security configuration class for JWT authentication.
 *
 * This class sets up the OpenAPI specification to include a bearer token
 * security scheme
 * using JWT, allowing secure access to protected endpoints in the Swagger UI.
 *
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
public class OpenApiSecurityConfig {
}
