/**
 * Data Transfer Object (DTO) representing a full user entity.
 *
 * This DTO is used to transfer user data between layers, especially when displaying
 * or updating user-related information. Includes identity, credentials, audit,
 * contact, and login-related fields.
 *
 * Author: Fatlum Epiroti
 * Version: 2.0
 * Date: 2025-06-06
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
    /** The unique identifier of the user. */
    @NotNull
    @Schema(description = "Id of the user", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    /** The username used for login. */
    @NotNull
    @NotBlank
    @Schema(description = "Username of the user", example = "userTim")
    private String username;

    /** The user's password (should be stored hashed). */
    @NotNull
    @NotBlank
    @Schema(description = "Password of the user", example = "secretPassword123")
    private String password;

    /** The user's first name. */
    @NotNull
    @NotBlank
    @Schema(description = "First name of the user", example = "Tim")
    private String firstName;

    /** The user's last name. */
    @NotNull
    @NotBlank
    @Schema(description = "Last name of the user", example = "Tester")
    private String lastName;

    /** The role assigned to the user (e.g., ADMIN, USER). */
    @NotNull
    @NotBlank
    @Schema(description = "Role of the user", example = "ADMIN")
    private String role;

    /** The user's email address. */
    @NotNull
    @NotBlank
    @Schema(description = "Email address of the user", example = "test@test.ch")
    private String eMail;

    /** The user's birth date. */
    @NotNull
    @Schema(description = "Birth date of the user", example = "12.12.2012")
    private Date birthdate;

    /** The user's street address. */
    @NotNull
    @NotBlank
    @Schema(description = "Address of the user", example = "Jurastrasse 15")
    private String address;

    /** The postal code of the user's address. */
    @NotNull
    @NotBlank
    @Schema(description = "ZipCode of the user", example = "3013")
    private String zipCode;

    /** The city where the user resides. */
    @NotNull
    @NotBlank
    @Schema(description = "City of the user", example = "Bern")
    private String city;

    /** The user's country of residence. */
    @NotNull
    @NotBlank
    @Schema(description = "Country of the user", example = "Schweiz")
    private String country;

    /** The user's phone number. */
    @NotNull
    @NotBlank
    @Schema(description = "Phone number of the user", example = "0790010101")
    private String phoneNumber;

    /** The username of the user who created this record. */
    @NotNull
    @NotBlank
    @Schema(description = "Created user of the user", example = "userTim")
    private String createdUser;

    /** The timestamp when this record was created. */
    @NotNull
    @Schema(description = "Created date of the user", example = "2023-10-01")
    private Date createdDate;

    /** The username of the user who last modified this record. */
    @NotNull
    @NotBlank
    @Schema(description = "Modified user of the user", example = "userTim")
    private String modifiedUser;

    /** The timestamp when this record was last modified. */
    @NotNull
    @Schema(description = "Modified date of the user", example = "2023-10-01")
    private Date modifiedDate;

    /** The timestamp of the user's last login. */
    @NotNull
    @Schema(description = "Last login date of the user", example = "2023-10-01")
    private Date lastLogin;

    /** The IP address from which the user last logged in. */
    @NotNull
    @NotBlank
    @Schema(description = "Last login IP of the user", example = "201.10.15.113")
    private String lastLoginIp;
}
