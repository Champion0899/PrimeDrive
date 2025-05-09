package com.example.PrimeDriveBackend.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlattformNutzerkonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer kontoId;

    @Column(nullable = false)
    private String benutzername;

    @Column(nullable = false)
    private String passwort;

    private String rolle;

    @Column(unique = true)
    private String eMail;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.singletonList(() -> "ROLE_" + this.rolle.toUpperCase());
    }
}