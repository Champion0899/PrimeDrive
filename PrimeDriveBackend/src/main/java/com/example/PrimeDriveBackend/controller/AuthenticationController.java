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
import java.util.UUID;

import org.springframework.http.ResponseEntity;

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