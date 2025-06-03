package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a type of fuel used by vehicles.
 *
 * Stores fuel-related information, such as fuel type name (e.g., Petrol,
 * Diesel, Electric),
 * typically used in vehicle specifications and search filters.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFuels {

    /** Unique identifier for the fuel type. */
    @Id
    private String id;

    /** Descriptive name of the fuel type (e.g., Petrol, Diesel, Electric). */
    private String fuelType;
}
