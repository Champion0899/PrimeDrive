package com.example.PrimeDriveBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KaufId implements Serializable {
    private Long kaeufer;
    private Long fahrzeug;
}
