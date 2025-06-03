package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing the seating configuration of a vehicle.
 *
 * Stores information about the number of seats, typically referenced in vehicle specifications.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSeats {

    /** Unique identifier for the seat configuration. */
    @Id
    private String id;

    /** Number of seats defined by this configuration. */
    private Integer quantity;
}
