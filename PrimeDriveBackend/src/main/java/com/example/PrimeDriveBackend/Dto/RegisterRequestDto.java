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

    @NotNull
    @Schema(description = "Address of the user", example = "Funnystreet 15", required = true)
    private String address;

    @NotNull
    @Schema(description = "ZipCode of the user", example = "3013", required = true)
    private Number zipCode;

    @NotNull
    @Schema(description = "City of the user", example = "John", required = true)
    private String city;

    @NotNull
    @Schema(description = "Country of the user", example = "John", required = true)
    private String country;

    @NotNull
    @Schema(description = "Phone number of the user", example = "John", required = true)
    private String phoneNumber;

    @NotNull
    @Schema(description = "Creator of the user", example = "John", required = true)
    private String createUser;

    @NotNull
    @Schema(description = "Created at", example = "John", required = true)
    private String createDate;

    @NotNull
    @Schema(description = "Modified user", example = "John", required = true)
    private String modifidUser;

    @NotNull
    @Schema(description = "Modified at", example = "John", required = true)
    private String modifidDate;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Number getZipCode() {
        return zipCode;
    }

    public void setZipCode(Number zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifidUser() {
        return modifidUser;
    }

    public void setModifidUser(String modifidUser) {
        this.modifidUser = modifidUser;
    }

    public String getModifidDate() {
        return modifidDate;
    }

    public void setModifidDate(String modifidDate) {
        this.modifidDate = modifidDate;
    }

}