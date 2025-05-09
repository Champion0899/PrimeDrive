package com.example.PrimeDriveBackend.Dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterRequestDto {

    @NotNull
    @Schema(description = "Username for registration", example = "myUsername", required = true)
    private String username;

    @NotNull
    @Schema(description = "Password for the new account", example = "securePassword123", required = true)
    private String password;

    @NotNull
    @Schema(description = "Role of the user", example = "ADMIN", required = true)
    private String role;

    @NotNull
    @Schema(description = "Email address of the user", example = "user@example.com", required = true)
    private String email;

    // Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}