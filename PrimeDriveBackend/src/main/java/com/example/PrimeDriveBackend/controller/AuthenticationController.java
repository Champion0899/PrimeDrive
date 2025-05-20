package com.example.PrimeDriveBackend.controller;

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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

/**
 * REST controller providing endpoints for user registration and login.
 * Handles incoming authentication requests and delegates processing to the AuthenticationService.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-05-16
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
     * Endpoint to register a new user.
     * Extracts the IP address from the request and passes all registration data to the service layer.
     *
     * @param httpRequest The incoming HTTP request used to extract client IP
     * @param request The registration request data (username, password, etc.)
     * @return ResponseEntity indicating successful registration
     */
    @PostMapping("/register")
    @CrossOrigin
    @Operation(summary = "Register a new user", description = "Registers a new user with the provided credentials.")
    public ResponseEntity<?> registerUser(HttpServletRequest httpRequest, @RequestBody RegisterRequestDto request) {
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
     * Endpoint to authenticate a user and return a JWT token if successful.
     *
     * @param request Login request containing username and password
     * @return JWT token and userId if authenticated, or 401 error otherwise
     */
    @PostMapping("/login")
    @CrossOrigin
    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token.")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        boolean isAuthenticated = authenticationService.login(
                request.getUsername(),
                request.getPassword());
        if (isAuthenticated) {
            Users user = userService
                    .findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String userId = user.getId();
            String token = jwtUtils.generateToken(userId);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", token);
            responseBody.put("userId", userId);
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}