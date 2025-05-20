package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSpecsDto {
    @NotNull
    @Schema(description = "Id of the vehicle specs", example = "48d183d9-5658-4488-984e-8801967850e9")
    String id;
    @NotNull
    @Schema(description = "Power in kW", example = "150")
    Integer powerKw;
    @NotNull
    @Schema(description = "Power in PS", example = "204")
    Integer powerPs;
    @NotNull
    @Schema(description = "Length in millimeters", example = "4500")
    Integer lengthMillimeter;
    @NotNull
    @Schema(description = "Width in millimeters", example = "1800")
    Integer widthMillimeter;
    @NotNull
    @Schema(description = "Height in millimeters", example = "1400")
    Integer heightMillimeter;
    @NotNull
    @Schema(description = "Minimum trunk volume in liters", example = "300")
    Integer trunkInLiterMin;
    @NotNull
    @Schema(description = "Maximum trunk volume in liters", example = "500")
    Integer trunkInLiterMax;
    @NotNull
    @Schema(description = "0-100 km/h time in seconds", example = "6.5")
    Double zeroToHundredInSeconds;
    @NotNull
    @Schema(description = "Top speed in km/h", example = "250")
    Integer topSpeedInKmh;
    @NotNull
    @Schema(description = "Consumption per 100 km in X", example = "7.5")
    Double consumptionHundredInX;
    @NotNull
    @Schema(description = "CO2 emission in g/km", example = "120")
    Integer coTwoEmissionInGPerKm;
    @NotNull
    @Schema(description = "Cubic capacity in liters", example = "2.0")
    Double cubicCapacity;
    @NotNull
    @Schema(description = "Number of doors", example = "4")
    String doorsId;
    @NotNull
    @Schema(description = "Number of seats", example = "5")
    String seatsId;
    @NotNull
    @Schema(description = "Engine type", example = "v6")
    String engineId;
    @NotNull
    @Schema(description = "Fuel type", example = "Petrol")
    String fuelsId;
}
