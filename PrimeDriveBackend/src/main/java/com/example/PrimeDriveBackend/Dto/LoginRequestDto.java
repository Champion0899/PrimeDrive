package com.example.PrimeDriveBackend.Dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequestDto {

    @NotNull
    @Schema(description = "username", example = "userTim", required = true)
    private String username;

    @NotNull
    @Schema(description = "Password", example = "secretPassword123", required = true)
    private String password;

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