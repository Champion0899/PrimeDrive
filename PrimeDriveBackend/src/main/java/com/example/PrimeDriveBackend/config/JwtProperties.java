package com.example.PrimeDriveBackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration properties class for JWT (JSON Web Token) settings.
 * 
 * This class binds properties defined under the "jwt" prefix in the application
 * configuration
 * file (e.g., application.yml or application.properties). It provides access to
 * the
 * secret key and token expiration time used for JWT generation and validation.
 * 
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    private String secret;
    private Integer expirationTime;
}
