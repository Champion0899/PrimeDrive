package com.example.PrimeDriveBackend.model;

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
    private Long kontoId;

    private String benutzername;
    private String passwort;
    private String rolle;

    @Column(unique = true)
    private String email;
}