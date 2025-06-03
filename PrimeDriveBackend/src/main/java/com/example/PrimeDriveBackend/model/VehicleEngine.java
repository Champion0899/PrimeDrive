package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing an engine configuration for a vehicle.
 *
 * Stores information about the type of engine used in a vehicle,
 * such as its classification (e.g., V6, Electric).
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEngine {

    /**
     * Unique identifier for the engine configuration.
     */
    @Id
    private String id;
    /**
     * Descriptive type of the engine (e.g., V6, Electric).
     */
    private String engineType;
}
