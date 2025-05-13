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
    private Integer kaeuferId;

    @Id
    private Integer fahrzeugId;

    @ManyToOne
    @JoinColumn(name = "kaeuferId", insertable = false, updatable = false)
    private Kaeufer kaeufer;

    @ManyToOne
    @JoinColumn(name = "fahrzeugId", insertable = false, updatable = false)
    private Vehicle fahrzeug;
}
