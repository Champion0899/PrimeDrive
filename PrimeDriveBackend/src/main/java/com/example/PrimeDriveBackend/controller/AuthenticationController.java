package com.example.PrimeDriveBackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.PlattformNutzerkontoService;
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
    private final PlattformNutzerkontoService plattformNutzerkontoService;

    @PostMapping("/register")
    @CrossOrigin
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
    @CrossOrigin
    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token.")
    public ResponseEntity<?> login(@RequestBody PlattformNutzerkonto request) {
        boolean isAuthenticated = authenticationService.login(
                request.getBenutzername(),
                request.getPasswort());
        if (isAuthenticated) {
            PlattformNutzerkonto plattformNutzerkonto = plattformNutzerkontoService
                    .findByUsername(request.getBenutzername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            int userId = plattformNutzerkonto.getKontoId();
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