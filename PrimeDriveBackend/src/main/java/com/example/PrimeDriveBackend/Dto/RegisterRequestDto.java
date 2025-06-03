package com.example.PrimeDriveBackend.Dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing a registration request.
 *
 * This class includes all required fields for user registration, such as credentials,
 * contact information, and personal details. It is used in the /register and /swagger-register
 * endpoints for creating new user accounts.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public class RegisterRequestDto {

    /** The desired username for the new user account. */
    @NotNull
    @Schema(description = "Username for registration", example = "myUsername", required = true)
    private String username;

    /** The password to be associated with the new account. */
    @NotNull
    @Schema(description = "Password for the new account", example = "securePassword123", required = true)
    private String password;

    /** The role assigned to the new user (e.g., USER, SELLER, ADMIN). */
    @NotNull
    @Schema(description = "Role of the user", example = "ADMIN", required = true)
    private String role;

    /** The birthdate of the user in dd.MM.yyyy format. */
    @NotNull
    @Schema(description = "Birth date of the user", example = "12.12.2012", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthdate;

    /** The email address of the user. */
    @NotNull
    @Schema(description = "Email address of the user", example = "user@example.com", required = true)
    private String eMail;

    /** The street address of the user. */
    @NotNull
    @Schema(description = "Address of the user", example = "Jurastrasse 15", required = true)
    private String address;

    /** The postal code of the user's address. */
    @NotNull
    @Schema(description = "ZipCode of the user", example = "3013", required = true)
    private String zipCode;

    /** The city of residence. */
    @NotNull
    @Schema(description = "City of the user", example = "Bern", required = true)
    private String city;

    /** The country of residence. */
    @NotNull
    @Schema(description = "Country of the user", example = "Schweiz", required = true)
    private String country;

    /** The phone number to be associated with the account. */
    @NotNull
    @Schema(description = "Phone number of the user", example = "0790010101", required = true)
    private String phoneNumber;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
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
}