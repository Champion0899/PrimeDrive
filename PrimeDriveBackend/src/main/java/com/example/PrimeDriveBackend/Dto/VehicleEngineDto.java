package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a vehicle engine configuration.
 *
 * This class is used to encapsulate details about the engine type of a vehicle,
 * including the unique identifier and descriptive engine characteristics.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEngineDto {
    /** The unique identifier for the engine configuration. */
    @NotNull
    @Schema(description = "Id of the engine", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    /** The type or classification of the engine (e.g., V6, V8, Electric). */
    @NotNull
    @Schema(description = "Engine type", example = "V6")
    private String engineType;
}
