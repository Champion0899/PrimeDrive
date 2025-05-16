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
    @JoinColumn(name = "vehicle_doors_id", referencedColumnName = "id")
    private VehicleDoors doors;

    @ManyToOne
    @JoinColumn(name = "vehicle_seats_id", referencedColumnName = "id")
    private VehicleSeats seats;

    @ManyToOne
    @JoinColumn(name = "vehicle_engine_id", referencedColumnName = "id")
    private VehicleEngine engine;

    @ManyToOne
    @JoinColumn(name = "vehicle_fuel_id", referencedColumnName = "id")
    private VehicleFuels fuels;
}
