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
    private Integer kontoId;

    @Column(nullable = false)
    private String benutzername;

    @Column(nullable = false)
    private String passwort;

    private String rolle;

    @Column(unique = true)
    private String eMail;
}