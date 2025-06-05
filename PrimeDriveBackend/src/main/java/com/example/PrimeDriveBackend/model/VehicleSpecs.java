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
 * Entity representing detailed technical specifications of a vehicle.
 *
 * Contains data such as dimensions, engine performance, consumption, emissions,
 * and relationships to other specifications like doors, seats, engine type, and
 * fuel.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSpecs {

    /** Unique identifier for the vehicle specifications. (UUID) */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Power output of the vehicle in kilowatts (kW). */
    private Integer powerKw;
    /** Power output of the vehicle in horsepower (PS). */
    private Integer powerPs;
    /** Length of the vehicle in millimeters. */
    private Integer lengthMillimeter;
    /** Width of the vehicle in millimeters. */
    private Integer widthMillimeter;
    /** Height of the vehicle in millimeters. */
    private Integer heightMillimeter;
    /** Minimum trunk capacity in liters. */
    private Integer trunkInLiterMin;
    /** Maximum trunk capacity in liters. */
    private Integer trunkInLiterMax;
    /** Acceleration time from 0 to 100 km/h in seconds. */
    private Double zeroToHundredInSeconds;
    /** Top speed of the vehicle in kilometers per hour (km/h). */
    private Integer topSpeedInKmh;
    /** Fuel consumption per 100 km (typically in liters). */
    @Column(name = "consumption_hundred_in_x")
    private Double consumptionHundredInX;
    /** CO₂ emissions per kilometer in grams. */
    @Column(name = "co_two_emission_in_g_per_km")
    private Integer coTwoEmissionInGPerKm;
    /** Cubic capacity of the engine in liters. */
    private Double cubicCapacity;

    /** Reference to the door configuration. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_doors_id", referencedColumnName = "id")
    private VehicleDoors doors;

    /** Reference to the seating configuration. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_seats_id", referencedColumnName = "id")
    private VehicleSeats seats;

    /** Reference to the engine configuration. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_engine_id", referencedColumnName = "id")
    private VehicleEngine engine;

    /** Reference to the fuel type. */
    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_fuels_id", referencedColumnName = "id")
    private VehicleFuels fuels;
}
