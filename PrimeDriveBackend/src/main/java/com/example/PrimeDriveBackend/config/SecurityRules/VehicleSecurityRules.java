package com.example.PrimeDriveBackend.config.SecurityRules;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class VehicleSecurityRules {
    public static void apply(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_brands/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_colors/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_doors/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_engine/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_fuels/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_holdings/**")
                .permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_seats/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_specs/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_types/**").permitAll()
                .requestMatchers("/api/vehicle/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_brands/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_colors/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_doors/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_engine/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_fuels/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_holdings/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_seats/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_specs/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_types/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .anyRequest().authenticated());
    }
}
