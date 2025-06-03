package com.example.PrimeDriveBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.PrimeDriveBackend.config.SecurityRules.AuthSecurityRules;
import com.example.PrimeDriveBackend.config.SecurityRules.SwaggerSecurityRules;
import com.example.PrimeDriveBackend.config.SecurityRules.UserSecurityRules;
import com.example.PrimeDriveBackend.config.SecurityRules.VehicleSecurityRules;
import com.example.PrimeDriveBackend.filter.JwtFilter;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Main Spring Security configuration for the PrimeDrive application.
 *
 * This class sets up security filters, session management, CORS/CSRF policies,
 * password encoding, JWT authentication, and applies custom route-based
 * security rules.
 *
 * It integrates specific security rule classes for Swagger, Authentication,
 * Vehicles, and Users,
 * and enforces stateless JWT-based security throughout the application.
 *
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configures the application's main security filter chain.
     *
     * - Disables CSRF (since the app is stateless)
     * - Enables CORS
     * - Applies stateless session management
     * - Applies specific security rules for Swagger, authentication, vehicles, and
     * users
     * - Adds JWT filter before the UsernamePasswordAuthenticationFilter
     *
     * @param http the HttpSecurity object used for configuring security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if any security configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        SwaggerSecurityRules.apply(http);
        AuthSecurityRules.apply(http);
        VehicleSecurityRules.apply(http);
        UserSecurityRules.apply(http);

        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}