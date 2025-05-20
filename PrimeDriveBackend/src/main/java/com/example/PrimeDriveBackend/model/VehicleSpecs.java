package com.example.PrimeDriveBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSpecs {

    @Id
    private String id;
    private Integer powerKw;
    private Integer powerPs;
    private Integer lengthMillimeter;
    private Integer widthMillimeter;
    private Integer heightMillimeter;
    private Integer trunkInLiterMin;
    private Integer trunkInLiterMax;
    private Double zeroToHundredInSeconds;
    private Integer topSpeedInKmh;
    @Column(name = "consumption_hundred_in_x")
    private Double consumptionHundredInX;
    @Column(name = "co_two_emission_in_g_per_km")
    private Integer coTwoEmissionInGPerKm;
    private Double cubicCapacity;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_doors_id", referencedColumnName = "id")
    private VehicleDoors doors;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_seats_id", referencedColumnName = "id")
    private VehicleSeats seats;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_engine_id", referencedColumnName = "id")
    private VehicleEngine engine;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_fuels_id", referencedColumnName = "id")
    private VehicleFuels fuels;
}
