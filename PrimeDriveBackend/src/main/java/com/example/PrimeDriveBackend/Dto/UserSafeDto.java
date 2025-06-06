package com.example.PrimeDriveBackend.dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) used for securely transferring user information.
 *
 * This DTO is designed to expose only non-sensitive user data, making it
 * suitable for
 * public API responses and views. Sensitive fields like passwords are
 * intentionally omitted.
 * It includes metadata such as creation and modification timestamps, contact
 * information,
 * and login history.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSafeDto {
    /** The unique identifier of the user. */
    @NotNull
    @Schema(description = "Id of the user", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    /** The username of the user. */
    @NotNull
    @Schema(description = "Username of the user", example = "userTim")
    private String username;

    /** The role assigned to the user (e.g., USER, ADMIN, SELLER). */
    @NotNull
    @Schema(description = "Role of the user", example = "ADMIN")
    private String role;

    /** The email address of the user. */
    @NotNull
    @Schema(description = "Email address of the user", example = "test@test.ch")
    private String eMail;

    /** The user's date of birth. */
    @NotNull
    @Schema(description = "Birth date of the user", example = "12.12.2012")
    private Date birthdate;

    /** The user's first name. */
    @NotNull
    @Schema(description = "First name of the user", example = "Tim")
    private String firstName;

    /** The user's last name. */
    @NotNull
    @Schema(description = "Last name of the user", example = "Tester")
    private String lastName;

    /** The street address of the user. */
    @NotNull
    @Schema(description = "Address of the user", example = "Jurastrasse 15")
    private String address;

    /** The postal or ZIP code of the user's address. */
    @NotNull
    @Schema(description = "ZipCode of the user", example = "3013")
    private String zipCode;

    /** The city of residence. */
    @NotNull
    @Schema(description = "City of the user", example = "Bern")
    private String city;

    /** The country of residence. */
    @NotNull
    @Schema(description = "Country of the user", example = "Schweiz")
    private String country;

    /** The user's phone number. */
    @NotNull
    @Schema(description = "Phone number of the user", example = "0790010101")
    private String phoneNumber;

    /** The user who created this account. */
    @NotNull
    @Schema(description = "Created user of the user", example = "userTim")
    private String createdUser;

    /** The date when this account was created. */
    @NotNull
    @Schema(description = "Created date of the user", example = "2023-10-01")
    private Date createdDate;

    /** The user who last modified this account. */
    @NotNull
    @Schema(description = "Modified user of the user", example = "userTim")
    private String modifiedUser;

    /** The date of the last modification. */
    @NotNull
    @Schema(description = "Modified date of the user", example = "2023-10-01")
    private Date modifiedDate;

    /** The date and time of the last login. */
    @NotNull
    @Schema(description = "Last login date of the user", example = "2023-10-01")
    private Date lastLogin;

    /** The IP address from which the user last logged in. */
    @NotNull
    @Schema(description = "Last login IP of the user", example = "201.10.15.113")
    private String lastLoginIp;
}
