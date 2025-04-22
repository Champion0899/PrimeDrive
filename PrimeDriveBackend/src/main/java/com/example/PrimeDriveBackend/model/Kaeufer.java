package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kaeufer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kaeuferId;

    private String name;
    private String vorname;

    @Column(unique = true)
    private String email;

    private String telefonnummer;
    private String adresse;
    private String praferenzen;

    @OneToOne
    @JoinColumn(name = "konto_id", referencedColumnName = "kontoId")
    private PlattformNutzerkonto konto;
}
