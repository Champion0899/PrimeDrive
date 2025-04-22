package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Probefahrt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long probefahrtId;

    private LocalDate datum;
    private LocalTime uhrzeit;
    private String ort;
    private String status;

    @OneToOne
    @JoinColumn(name = "kaeufer_id", unique = true)
    private Kaeufer kaeufer;

    @OneToOne
    @JoinColumn(name = "fahrzeug_id", unique = true)
    private Fahrzeug fahrzeug;
}
