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
public class Finanzierungsoption {

    @Id
    private Integer finanzierungId;

    private String art;

    private Double zinssatz;

    private Integer laufzeit;

    private Double monatlicheRate;

    @OneToOne
    @JoinColumn(name = "fahrzeugId")
    private Fahrzeug fahrzeug;
}
