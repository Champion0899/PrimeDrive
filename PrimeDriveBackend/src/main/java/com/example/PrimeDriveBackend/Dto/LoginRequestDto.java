package com.example.PrimeDriveBackend.Dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequestDto {

    @NotNull
    @Schema(description = "username", example = "userTim", required = true)
    private String benutzername;

    @NotNull
    @Schema(description = "Password", example = "secretPassword123", required = true)
    private String passwort;

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}