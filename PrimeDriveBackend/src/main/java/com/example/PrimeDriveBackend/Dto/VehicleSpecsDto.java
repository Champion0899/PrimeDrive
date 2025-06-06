package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the technical specifications of a vehicle.
 *
 * This class contains detailed technical and performance data such as dimensions,
 * engine output, fuel consumption, and emission values. It also links to related entities
 * like doors, seats, engine, and fuel type.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSpecsDto {
    /** The unique identifier for the vehicle specifications. */
    @NotNull
    @Schema(description = "Id of the vehicle specs", example = "48d183d9-5658-4488-984e-8801967850e9")
    String id;

    /** Power output in kilowatts (kW). */
    @NotNull
    @Schema(description = "Power in kW", example = "150")
    Integer powerKw;

    /** Power output in horsepower (PS). */
    @NotNull
    @Schema(description = "Power in PS", example = "204")
    Integer powerPs;

    /** Vehicle length in millimeters. */
    @NotNull
    @Schema(description = "Length in millimeters", example = "4500")
    Integer lengthMillimeter;

    /** Vehicle width in millimeters. */
    @NotNull
    @Schema(description = "Width in millimeters", example = "1800")
    Integer widthMillimeter;

    /** Vehicle height in millimeters. */
    @NotNull
    @Schema(description = "Height in millimeters", example = "1400")
    Integer heightMillimeter;

    /** Minimum trunk volume in liters. */
    @NotNull
    @Schema(description = "Minimum trunk volume in liters", example = "300")
    Integer trunkInLiterMin;

    /** Maximum trunk volume in liters. */
    @NotNull
    @Schema(description = "Maximum trunk volume in liters", example = "500")
    Integer trunkInLiterMax;

    /** Acceleration time from 0 to 100 km/h in seconds. */
    @NotNull
    @Schema(description = "0-100 km/h time in seconds", example = "6.5")
    Double zeroToHundredInSeconds;

    /** Maximum speed in kilometers per hour. */
    @NotNull
    @Schema(description = "Top speed in km/h", example = "250")
    Integer topSpeedInKmh;

    /** Fuel consumption per 100 km (unit depends on fuel type). */
    @NotNull
    @Schema(description = "Consumption per 100 km in X", example = "7.5")
    Double consumptionHundredInX;

    /** CO2 emissions in grams per kilometer. */
    @NotNull
    @Schema(description = "CO2 emission in g/km", example = "120")
    Integer coTwoEmissionInGPerKm;

    /** Engine displacement or cubic capacity in liters. */
    @NotNull
    @Schema(description = "Cubic capacity in liters", example = "2.0")
    Double cubicCapacity;

    /** Reference ID for door configuration. */
    @NotNull
    @Schema(description = "Number of doors", example = "4")
    String doorsId;

    /** Reference ID for seat configuration. */
    @NotNull
    @Schema(description = "Number of seats", example = "5")
    String seatsId;

    /** Reference ID for engine type. */
    @NotNull
    @Schema(description = "Engine type", example = "v6")
    String engineId;

    /** Reference ID for fuel type. */
    @NotNull
    @Schema(description = "Fuel type", example = "Petrol")
    String fuelsId;
}
