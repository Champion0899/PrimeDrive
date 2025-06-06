package com.example.PrimeDriveBackend.dto;

import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a vehicle holding or parent company.
 *
 * This class encapsulates information about vehicle holdings, such as the holding's name,
 * founding year, and associated branding (e.g., logo). Used for organizing vehicle brands.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleHoldingsDto {
    /** The unique identifier for the vehicle holding. */
    @NotNull
    @Schema(description = "Id of the vehicle holding", example = "48d183d9-5658-4488-984e-8801967850e9")
    String id;

    /** The name of the vehicle holding company. */
    @NotNull
    @Schema(description = "Name of the vehicle holding", example = "Volkswagen AG")
    String name;

    /** The year the holding company was founded. */
    @NotNull
    @Min(1)
    @Schema(description = "Founding date of the vehicle holding", example = "2020", minimum = "1")
    Integer founding;

    /** The URL to the logo image of the vehicle holding. */
    @NotNull
    @Schema(description = "Logo of the vehicle holding", example = "https://example.com/logo.jpg")
    String logo;
}
