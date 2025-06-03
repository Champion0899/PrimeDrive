package com.example.PrimeDriveBackend.Dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing a login request.
 *
 * This class contains the necessary credentials used for user authentication,
 * including a username and password. Used in the /login and /swagger-login
 * endpoints.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public class LoginRequestDto {

    /**
     * The username of the user attempting to log in.
     */
    @NotNull
    @Schema(description = "username", example = "userTim", required = true)
    private String username;

    /**
     * The password associated with the user's account.
     */
    @NotNull
    @Schema(description = "Password", example = "secretPassword123", required = true)
    private String password;

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
}