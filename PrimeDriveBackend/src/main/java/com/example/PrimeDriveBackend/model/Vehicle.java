package com.example.PrimeDriveBackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a vehicle listed on the PrimeDrive platform.
 *
 * Contains basic vehicle details such as name, price, year, and condition,
 * and links to related entities like brand, specifications, type, color, and
 * seller.
 *
 * Author: Fatlum Epiroti, Jamie Schüpbach & Lorin Baumann
 * Version: 2.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    /** Unique identifier for the vehicle. (UUID) */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Name or model designation of the vehicle. */
    private String name;
    /** Listed price of the vehicle. */
    private Double price;
    /** Manufacturing year of the vehicle. */
    private Integer year;
    /** URL or path to the vehicle's representative image. */
    private String image;
    /** Mileage of the vehicle (distance already driven). */
    private Integer mileage;
    /** Condition status of the vehicle (e.g., new, used). */
    private String condition;
    /** Historical notes about the vehicle (e.g., accident-free). */
    private String vehicleHistory;

    /** Reference to the vehicle's brand. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_brands_id", referencedColumnName = "id")
    private VehicleBrands brands;

    /** Reference to the technical specifications of the vehicle. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_specs_id", referencedColumnName = "id")
    private VehicleSpecs specs;

    /** Reference to the vehicle's type or category. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_types_id", referencedColumnName = "id")
    private VehicleTypes types;

    /** Reference to the vehicle's color. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_colors_id", referencedColumnName = "id")
    private VehicleColors colors;

    /** Reference to the seller (user) of the vehicle. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_seller_id", referencedColumnName = "id")
    private Users users;
}
