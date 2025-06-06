package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a fuel type for vehicles.
 *
 * This class is used to encapsulate information about the fuel used by a
 * vehicle,
 * such as petrol, diesel, electric, or hybrid types, and includes a unique
 * identifier.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFuelsDto {
    /** The unique identifier for the fuel type. */
    @NotNull
    @Schema(description = "Id of the fuel", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    /** The type of fuel used by the vehicle (e.g., Petrol, Diesel, Electric). */
    @NotNull
    @jakarta.validation.constraints.NotBlank
    @Schema(description = "Fuel type", example = "Petrol")
    private String fuelType;
}
