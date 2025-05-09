package com.example.PrimeDriveBackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.util.JwtUtil;

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
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerPlattformNutzer(@RequestBody PlattformNutzerkonto request) {
        authenticationService.registerPlattformNutzer(
                request.getBenutzername(),
                request.getPasswort(),
                request.getRolle(),
                request.getEMail());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
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