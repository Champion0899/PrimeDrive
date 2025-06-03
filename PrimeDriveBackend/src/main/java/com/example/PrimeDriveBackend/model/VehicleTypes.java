package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing the type or category of a vehicle.
 *
 * Stores information such as the vehicle's classification (e.g., SUV, Sedan,
 * Hatchback).
 * Typically used for filtering and categorizing vehicles on the platform.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypes {
    /** Unique identifier for the vehicle type. */
    @Id
    private String id;
    /** Descriptive name of the vehicle type (e.g., SUV, Sedan). */
    private String type;
}
