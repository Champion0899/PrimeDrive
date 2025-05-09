package com.example.PrimeDriveBackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtils;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user with the provided credentials.")
    public ResponseEntity<?> registerPlattformNutzer(@RequestBody PlattformNutzerkonto request) {
        authenticationService.registerPlattformNutzer(
                request.getBenutzername(),
                request.getPasswort(),
                request.getRolle(),
                request.getEMail());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token.")
    public ResponseEntity<?> login(@RequestBody PlattformNutzerkonto request) {
        boolean isAuthenticated = authenticationService.login(
                request.getBenutzername(),
                request.getPasswort());
        if (isAuthenticated) {
            String token = jwtUtils.generateToken(request.getKontoId());
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", token);
            responseBody.put("userId", request.getKontoId());
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}