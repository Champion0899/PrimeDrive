package com.example.PrimeDriveBackend.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.util.Optional;

import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.service.UserService;
import com.example.PrimeDriveBackend.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter for processing JWT authentication headers in incoming HTTP requests.
 * Verifies the presence and validity of a JWT token and, upon successful
 * validation,
 * sets the authentication in the Spring Security Context.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-05-16
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    /**
     * Constructor for initializing the filter with the required services.
     *
     * @param jwtUtil     Utility class for extracting and validating JWT tokens
     * @param userService Service for retrieving user information by extracted ID
     */
    public JwtFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * Processes the HTTP request, checks for a valid JWT in the Authorization
     * header,
     * and sets the user authentication in the Spring Security Context upon
     * successful validation.
     *
     * @param request     The incoming HTTP request
     * @param response    The HTTP response
     * @param filterChain The filter chain to forward the request
     * @throws ServletException In case of servlet-specific errors
     * @throws IOException      In case of I/O errors
     */
    @Override
    protected void doFilterInternal(@org.springframework.lang.NonNull HttpServletRequest request,
            @org.springframework.lang.NonNull HttpServletResponse response,
            @org.springframework.lang.NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String id = null;

        // 1. Try Authorization Header first (used by Swagger etc.)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            System.out.println("🔐 Bearer token found in Authorization header");
        }

        // 2. If no header token found, try cookies (used by frontend)
        if (jwt == null) {
            var cookies = request.getCookies();
            if (cookies != null) {
                for (jakarta.servlet.http.Cookie cookie : cookies) {
                    if ("jwt".equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        System.out.println("🍪 JWT Cookie found: " + jwt);
                        break;
                    }
                }
            } else {
                System.out.println("⚠️ No cookies found in request");
            }
        }

        // 3. Validate token and extract user ID
        if (jwt != null) {
            try {
                id = jwtUtil.extractUserId(jwt);
                System.out.println("✅ Valid JWT, extracted ID: " + id);
            } catch (Exception e) {
                System.out.println("❌ Invalid JWT: " + e.getMessage());
            }
        }

        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Users> userDetails = userService.findById(id);
            System.out.println("🔍 User found: " + userDetails.isPresent());
            if (userDetails.isPresent() && jwtUtil.validateToken(jwt, userDetails.get())) {
                System.out.println("✅ Token validated, setting authentication");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.get().getId().toString(), null, userDetails.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("❌ Token validation failed");
            }
        } else {
            if (id == null) {
                System.out.println("❌ ID extraction failed, skipping authentication");
            } else {
                System.out.println("⚠️ SecurityContext already has authentication");
            }
        }
        filterChain.doFilter(request, response);
    }

}
