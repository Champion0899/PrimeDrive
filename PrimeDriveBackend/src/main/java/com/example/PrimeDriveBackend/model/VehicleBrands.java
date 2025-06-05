package com.example.PrimeDriveBackend.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a vehicle brand associated with a holding company.
 *
 * Contains brand-specific information such as name, founding year, and logo,
 * and is linked to a VehicleHoldings entity that owns the brand.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBrands {

    /** Unique identifier for the vehicle brand. (UUID) */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Name of the vehicle brand. */
    private String name;
    /** Year the brand was founded. */
    private Integer founding;
    /** URL or path to the brand's logo image. */
    private String logo;

    /** The holding company associated with this vehicle brand. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_holding_id", referencedColumnName = "id")
    private VehicleHoldings holding;
}
