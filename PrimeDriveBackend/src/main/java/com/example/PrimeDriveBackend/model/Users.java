package com.example.PrimeDriveBackend.model;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Date birthdate;

    @Column(unique = true)
    private String eMail;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String createdUser;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private String modifiedUser;

    @Column(nullable = false)
    private Date modifiedDate;

    @Column(nullable = false)
    private Date lastLoginDate;

    @Column(nullable = false)
    private String lastLoginIp;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.singletonList(() -> "ROLE_" + this.role.toUpperCase());
    }
}