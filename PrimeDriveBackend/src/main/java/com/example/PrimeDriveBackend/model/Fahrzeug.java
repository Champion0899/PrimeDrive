package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fahrzeug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fahrzeugId;

    private String marke;
    private String modell;
    private Integer baujahr;
    private Integer kilometerstand;
    private BigDecimal preis;
    private String zustand;
    private String fahrzeughistorie;

    @ManyToOne
    @JoinColumn(name = "verkaeufer_id")
    private Verkaeufer verkaeufer;
}
