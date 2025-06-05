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
 * Entity representing a vehicle holding company.
 *
 * Stores company-level data such as name, founding year, and logo. This entity
 * is associated with one or more vehicle brands under its ownership.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHoldings {
    /** Unique identifier for the vehicle holding (UUID). */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Name of the holding company. */
    private String name;
    /** Year the company was founded. */
    private Integer founding;
    /** URL or path to the company logo image. */
    private String logo;
}
