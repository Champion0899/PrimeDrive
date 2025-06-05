package com.example.PrimeDriveBackend.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
    /** Unique identifier for the vehicle color. (UUID) */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Name of the color (e.g., "Midnight Black"). */
    private String name;

    /** Hexadecimal color code used for UI representation (e.g., "#000000"). */
    private String hexCode;
}
