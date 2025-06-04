package com.example.PrimeDriveBackend.model;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a user of the PrimeDrive platform.
 *
 * Stores personal and account-related information including authentication
 * credentials,
 * contact details, audit metadata, and last login activity.
 *
 * Author: Fatlum Epiroti, Jamie Schüpbach & Lorin Baumann
 * Version: 2.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    /** Unique identifier for the user (UUID). */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Username used for login. */
    @Column(nullable = false)
    private String username;

    /** Hashed password for authentication. */
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    /** Role of the user (e.g., USER, ADMIN). */
    @Column(nullable = false)
    private String role;

    /** Birthdate of the user. */
    @Column(nullable = false)
    private Date birthdate;

    /** Unique email address of the user. */
    @Column(unique = true)
    private String eMail;

    /** Street address of the user. */
    @Column(nullable = false)
    private String address;

    /** ZIP/postal code of the user's address. */
    @Column(nullable = false)
    private String zipCode;

    /** City of residence. */
    @Column(nullable = false)
    private String city;

    /** Country of residence. */
    @Column(nullable = false)
    private String country;

    /** Contact phone number. */
    @Column(nullable = false)
    private String phoneNumber;

    /** Username of the account creator. */
    @Column(nullable = false)
    private String createdUser;

    /** Date the user record was created. */
    @Column(nullable = false)
    private Date createdDate;

    /** Username of the last person to modify the record. */
    @Column(nullable = false)
    private String modifiedUser;

    /** Date of the last modification. */
    @Column(nullable = false)
    private Date modifiedDate;

    /** Timestamp of the last successful login. */
    @Column(nullable = false)
    private Date lastLoginDate;

    /** IP address used at the last login. */
    @Column(nullable = false)
    private String lastLoginIp;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.singletonList(() -> "ROLE_" + this.role.toUpperCase());
    }
}