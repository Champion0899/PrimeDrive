package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private Integer fahrzeugId;

    private String image;

    private String marke;

    private String modell;

    private Integer baujahr;

    private Integer kilometerstand;

    private Double preis;

    private String zustand;

    @Lob
    private String fahrzeughistorie;

    @ManyToOne
    @JoinColumn(name = "verkaeuferId")
    private Verkaeufer verkaeufer;
}
