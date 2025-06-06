/**
 * Data Transfer Object (DTO) representing a full user entity.
 *
 * This DTO is used to transfer user data between layers, especially when displaying
 * or updating user-related information. Includes identity, credentials, audit,
 * contact, and login-related fields.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
package com.example.PrimeDriveBackend.dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    @Schema(description = "Id of the user", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    @NotNull
    @NotBlank
    @Schema(description = "Username of the user", example = "userTim")
    private String username;

    @NotNull
    @NotBlank
    @Schema(description = "Password of the user", example = "secretPassword123")
    private String password;

    @NotNull
    @NotBlank
    @Schema(description = "First name of the user", example = "Tim")
    private String firstName;

    @NotNull
    @NotBlank
    @Schema(description = "Last name of the user", example = "Tester")
    private String lastName;

    @NotNull
    @NotBlank
    @Schema(description = "Role of the user", example = "ADMIN")
    private String role;

    @NotNull
    @NotBlank
    @Schema(description = "Email address of the user", example = "test@test.ch")
    private String eMail;

    @NotNull
    @Schema(description = "Birth date of the user", example = "12.12.2012")
    private Date birthdate;

    @NotNull
    @NotBlank
    @Schema(description = "Address of the user", example = "Jurastrasse 15")
    private String address;

    @NotNull
    @NotBlank
    @Schema(description = "ZipCode of the user", example = "3013")
    private String zipCode;

    @NotNull
    @NotBlank
    @Schema(description = "City of the user", example = "Bern")
    private String city;

    @NotNull
    @NotBlank
    @Schema(description = "Country of the user", example = "Schweiz")
    private String country;

    @NotNull
    @NotBlank
    @Schema(description = "Phone number of the user", example = "0790010101")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Schema(description = "Created user of the user", example = "userTim")
    private String createdUser;

    @NotNull
    @Schema(description = "Created date of the user", example = "2023-10-01")
    private Date createdDate;

    @NotNull
    @NotBlank
    @Schema(description = "Modified user of the user", example = "userTim")
    private String modifiedUser;

    @NotNull
    @Schema(description = "Modified date of the user", example = "2023-10-01")
    private Date modifiedDate;

    @NotNull
    @Schema(description = "Last login date of the user", example = "2023-10-01")
    private Date lastLogin;

    @NotNull
    @NotBlank
    @Schema(description = "Last login IP of the user", example = "201.10.15.113")
    private String lastLoginIp;
}
