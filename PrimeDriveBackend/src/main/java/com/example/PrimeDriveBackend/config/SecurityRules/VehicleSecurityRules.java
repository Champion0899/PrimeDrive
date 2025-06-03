package com.example.PrimeDriveBackend.config.SecurityRules;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Defines security rules for vehicle-related API endpoints.
 *
 * This class manages access control for all vehicle-related resources, allowing
 * public access to
 * GET requests while restricting write and modification access to authenticated
 * users with specific roles.
 * 
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
public class VehicleSecurityRules {
    /**
     * Configures HTTP security rules for all /api/vehicle-related endpoints.
     *
     * - Allows public GET access to vehicle and vehicle specification endpoints.
     * - Restricts all other requests to users with roles: USER, ADMIN, or SELLER.
     *
     * @param http the HttpSecurity object used to configure web based security for
     *             specific http requests
     * @throws Exception if an error occurs while configuring the security settings
     */
    public static void apply(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_brands/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_colors/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_doors/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_engine/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_fuels/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_holdings/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_seats/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_specs/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/vehicle_types/**").permitAll()
                .requestMatchers("/api/vehicle/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_brands/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_colors/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_doors/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_engine/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_fuels/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_holdings/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_seats/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_specs/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER")
                .requestMatchers("/api/vehicle_types/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER"));
    }
}
