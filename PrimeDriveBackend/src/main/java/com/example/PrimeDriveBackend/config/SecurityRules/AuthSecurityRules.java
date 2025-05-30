package com.example.PrimeDriveBackend.config.SecurityRules;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class AuthSecurityRules {
    public static void apply(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/api/authentication/login",
                        "/api/authentication/register",
                        "/api/authentication/check-session",
                        "/api/authentication/swagger-login",
                        "/api/authentication/swagger-register")
                .permitAll());
    }
}
