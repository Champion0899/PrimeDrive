package com.example.PrimeDriveBackend.config.SecurityRules;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Defines security rules for user-related API endpoints.
 *
 * This class specifies public, authenticated, and role-based access to various
 * user-related routes,
 * such as fetching user profiles, retrieving the current user, and accessing
 * broader user resources.
 * 
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
public class UserSecurityRules {
    /**
     * Configures HTTP security rules for endpoints under /api/users.
     *
     * - Allows unrestricted access to /api/users/{id}.
     * - Requires authentication for /api/users/current.
     * - Requires specific roles (USER, ADMIN, SELLER) for other /api/users/**
     * routes.
     *
     * @param http the HttpSecurity object used to configure web based security for
     *             specific http requests
     * @throws Exception if an error occurs while configuring the security settings
     */
    public static void apply(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/{id}").permitAll()
                .requestMatchers("/api/users/current").authenticated()
                .requestMatchers("/api/users/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER"));
    }
}