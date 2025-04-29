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
    private Integer kaeuferId;

    private String name;

    private String vorname;

    @Column(unique = true)
    private String eMail;

    private String telefonnummer;

    private String adresse;

    @Lob
    private String praeferenzen;

    @OneToOne
    @JoinColumn(name = "kontoId", unique = true)
    private PlattformNutzerkonto konto;
}
