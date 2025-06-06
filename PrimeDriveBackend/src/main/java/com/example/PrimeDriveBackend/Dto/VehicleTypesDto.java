package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the type or category of a vehicle.
 *
 * This class is used to describe different vehicle classifications, such as
 * SUV, sedan, or coupe.
 * It includes a unique identifier and the name of the vehicle type.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypesDto {
    /** The unique identifier for the vehicle type. */
    @NotNull
    @Schema(description = "Id of vehicle types", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    /** The name or classification of the vehicle type (e.g., SUV, Sedan). */
    @NotNull
    @Schema(description = "Type of vehicle", example = "SUV")
    private String type;
}
