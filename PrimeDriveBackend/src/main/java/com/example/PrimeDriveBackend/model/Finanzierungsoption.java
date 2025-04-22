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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long finanzierungId;

    private String art;
    private BigDecimal zinssatz;
    private Integer laufzeit;
    private BigDecimal monatlicheRate;

    @ManyToOne
    @JoinColumn(name = "fahrzeug_id")
    private Fahrzeug fahrzeug;
}
