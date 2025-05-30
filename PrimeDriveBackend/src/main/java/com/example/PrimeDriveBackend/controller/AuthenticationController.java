package com.example.PrimeDriveBackend.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeDriveBackend.Dto.LoginRequestDto;
import com.example.PrimeDriveBackend.Dto.RegisterRequestDto;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.RequestInfoService;
import com.example.PrimeDriveBackend.service.UserService;
import com.example.PrimeDriveBackend.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseCookie;
import java.time.Duration;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * REST controller providing endpoints for user registration and login.
 * Handles incoming authentication requests and delegates processing to the
 * AuthenticationService.
 *
 * Author: Fatlum Epiroti
 * Version: 2.0
 * Date: 2025-05-30
 */
//
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtils;
    private final UserService userService;
    private final RequestInfoService requestInfoService;

    /**
     * Enables or disables the Swagger-only admin registration endpoint.
     * Controlled via 'swagger.setup.enabled' property in application.properties.
     * Defaults to true if not specified.
     */
    @Value("${swagger.setup.enabled:true}")
    private boolean swaggerSetupEnabled;

    /**
     * Endpoint to register a new user.
     * Extracts the IP address from the request and passes all registration data to
     * the service layer.
     * 
     * This endpoint explicitly prohibits the creation of ADMIN users.
     * To create ADMIN users, use the /swagger-register endpoint during setup phase
     * only.
     *
     * @param httpRequest The incoming HTTP request used to extract client IP
     * @param request     The registration request data (username, password, etc.)
     * @return ResponseEntity indicating successful registration or forbidden
     *         attempt
     */
    @PostMapping("/register")
    @CrossOrigin
    @Operation(summary = "Register a new user", description = "Registers a new user with the provided credentials. ADMIN role is forbidden here. Use /swagger-register only for initial setup.")
    public ResponseEntity<?> registerUser(HttpServletRequest httpRequest, @RequestBody RegisterRequestDto request) {
        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            return ResponseEntity.status(403).body("Admin registration is not allowed through this endpoint.");
        }

        String lastLoginIp = requestInfoService.getClientIp(httpRequest);
        authenticationService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole(),
                request.getEmail(),
                request.getBirthdate(),
                request.getAddress(),
                request.getZipCode(),
                request.getCity(),
                request.getCountry(),
                request.getPhoneNumber(), lastLoginIp);
        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Endpoint to authenticate a user and set up a secure server-side session.
     *
     * On successful login:
     * - Generates a JWT token
     * - Stores the token as a secure, HttpOnly cookie
     * - Initializes the Spring SecurityContext with the authenticated user
     *
     * This enables session-based authentication for further requests.
     *
     * @param request  Login request containing username and password
     * @param response HttpServletResponse used to set the JWT cookie
     * @return JSON response confirming login if authentication was successful;
     *         otherwise, 401 error
     */
    @PostMapping("/login")
    @CrossOrigin
    @Operation(summary = "User login", description = "Authenticates a user and sets a JWT as HttpOnly cookie. Returns a JSON message object upon login.")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpServletResponse response) {
        boolean isAuthenticated = authenticationService.login(
                request.getUsername(),
                request.getPassword());
        if (isAuthenticated) {
            String userId = userService.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"))
                    .getId();
            String token = jwtUtils.generateToken(userId);

            String cookieHeader = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .sameSite("None")
                    .maxAge(Duration.ofDays(7))
                    .build()
                    .toString();
            response.setHeader("Set-Cookie", cookieHeader);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Login successful");
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    /**
     * Endpoint to log out a user by clearing the JWT cookie.
     *
     * @param response HttpServletResponse used to clear the cookie
     * @return ResponseEntity confirming logout
     */
    @PostMapping("/logout")
    @CrossOrigin
    @Operation(summary = "Logout user", description = "Logs out the user by removing the JWT cookie from the browser. "
            + "The cookie is cleared by setting its max age to 0, effectively deleting it. "
            + "The JWT is stored as a HttpOnly, Secure, SameSite cookie and is not accessible to client-side scripts.")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Deletes the cookie
        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out");
    }

    /**
     * Swagger-only register endpoint to create ADMIN users for initial setup.
     * 
     * This endpoint is controlled by the 'swagger.setup.enabled' property.
     * It is intended for development and setup use only, and should be disabled in
     * production.
     *
     * @param httpRequest The incoming HTTP request used to extract client IP
     * @param request     The registration request data (must have role=ADMIN)
     * @return 200 if admin user registered, 403 if disabled or role not ADMIN
     */
    @PostMapping("/swagger-register")
    @Operation(summary = "Swagger admin registration", description = "Registers an ADMIN user for initial system setup via Swagger. Only enabled if swagger.setup.enabled=true. This endpoint should be disabled in production.")
    public ResponseEntity<?> swaggerRegister(HttpServletRequest httpRequest, @RequestBody RegisterRequestDto request) {
        if (!swaggerSetupEnabled) {
            return ResponseEntity.status(403).body("Swagger admin registration is disabled.");
        }

        if (!"ADMIN".equalsIgnoreCase(request.getRole())) {
            return ResponseEntity.status(403).body("Only ADMIN role allowed in swagger-register");
        }

        String lastLoginIp = requestInfoService.getClientIp(httpRequest);
        authenticationService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole(),
                request.getEmail(),
                request.getBirthdate(),
                request.getAddress(),
                request.getZipCode(),
                request.getCity(),
                request.getCountry(),
                request.getPhoneNumber(), lastLoginIp);
        return ResponseEntity.ok("Admin user registered successfully (Swagger)");
    }

    /**
     * Swagger-only login endpoint that returns JWT in body instead of cookie.
     * Useful for Swagger testing tools that require direct token access.
     * This endpoint is controlled by the 'swagger.setup.enabled' property.
     */
    @PostMapping("/swagger-login")
    @Operation(summary = "Swagger login (JWT in body)", description = "Authenticates a user and returns a JWT in the response body for Swagger testing. Only available if swagger.setup.enabled=true. This endpoint should be disabled in production.")
    public ResponseEntity<?> swaggerLogin(@RequestBody LoginRequestDto request) {
        if (!swaggerSetupEnabled) {
            return ResponseEntity.status(403).body("Swagger login is disabled.");
        }

        boolean isAuthenticated = authenticationService.login(
                request.getUsername(),
                request.getPassword());
        if (isAuthenticated) {
            Users user = userService
                    .findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String userId = user.getId();
            String token = jwtUtils.generateToken(userId);
            return ResponseEntity.ok(Map.of("userId", userId, "token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    /**
     * Endpoint to check if the user is authenticated via session.
     * 
     * This can be used by the frontend to determine login state.
     * It checks if a valid authentication object exists in the security context.
     *
     * @param request The incoming HTTP request (not used but required for
     *                signature)
     * @return true if authenticated, false otherwise
     */
    @GetMapping("/check-session")
    @Operation(summary = "Check session authentication", description = "Returns true if the user is authenticated via session. Useful for frontend login state checks.")
    public ResponseEntity<Boolean> checkSession(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
        return ResponseEntity.ok(isAuthenticated);
    }
}