package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing the number of doors in a vehicle.
 *
 * Used to define and categorize different vehicle door configurations,
 * typically referenced by vehicle specifications.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDoors {

    /**
     * Unique identifier for the door configuration.
     */
    @Id
    private String id;

    /**
     * Number of doors defined by this configuration.
     */
    private Integer quantity;
}
