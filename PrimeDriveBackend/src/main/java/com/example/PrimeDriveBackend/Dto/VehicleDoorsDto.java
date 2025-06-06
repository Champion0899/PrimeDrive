package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a vehicle door configuration.
 *
 * This class is used to encapsulate information about the number of doors
 * a vehicle has, typically for use in vehicle specifications or listings.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDoorsDto {
    /** The unique identifier for the door configuration. */
    @NotNull
    @Schema(description = "Id of the vehicle doors", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    /** The number of doors for the vehicle (e.g., 2, 4, etc.). */
    @NotNull
    @Schema(description = "Quantity of the vehicle doors", example = "4")
    private Integer quantity;
}
