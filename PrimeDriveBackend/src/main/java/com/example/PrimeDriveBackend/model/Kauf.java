package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(KaufId.class)
public class Kauf {

    @Id
    @ManyToOne
    @JoinColumn(name = "kaeufer_id")
    private Kaeufer kaeufer;

    @Id
    @ManyToOne
    @JoinColumn(name = "fahrzeug_id")
    private Fahrzeug fahrzeug;
}
