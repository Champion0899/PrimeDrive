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
    private Integer probefahrtId;

    private LocalDate datum;

    private LocalTime uhrzeit;

    private String ort;

    private String status;

    @ManyToOne
    @JoinColumn(name = "kaeuferId")
    private Kaeufer kaeufer;

    @OneToOne
    @JoinColumn(name = "fahrzeugId", unique = true)
    private Vehicle fahrzeug;
}
