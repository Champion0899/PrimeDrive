package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a color option for vehicles.
 *
 * Stores the color name and its corresponding hexadecimal color code.
 * This entity is used to categorize vehicles by visual appearance.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleColors {
    /** Unique identifier for the vehicle color. */
    @Id
    private String id;

    /** Name of the color (e.g., "Midnight Black"). */
    private String name;

    /** Hexadecimal color code used for UI representation (e.g., "#000000"). */
    private String hexCode;
}
