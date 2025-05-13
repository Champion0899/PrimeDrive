package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verkaeufer {

    @Id
    private Integer verkaeuferId;

    private String name;

    private String vorname;

    @Column(unique = true)
    private String eMail;

    private String telefonnummer;

    private String adresse;

    @OneToOne
    @JoinColumn(name = "kontoId", unique = true)
    private Users konto;
}
