package com.example.PrimeDriveBackend.model;

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
    private Number powerKw;
    private Number powerPs;
    private Number lengthMillimeter;
    private Number widthMillimeter;
    private Number heightMillimeter;
    private Number trunkInLiterMin;
    private Number trunkInLiterMax;
    private Double zeroToHundredInSeconds;
    private Number topSpeedInKmh;
    private Double consumptionHundredInX;
    private Number coTwoEmissionInGPerKm;
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
