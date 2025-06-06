package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data Transfer Object (DTO) representing the seating configuration of a
 * vehicle.
 *
 * This class is used to encapsulate details about the number of seats in a
 * vehicle,
 * typically used for classification, user interface display, or vehicle search
 * filters.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public class VehicleSeatsDto {
    /** The unique identifier for the seat configuration. */
    @NotNull
    @Schema(description = "Id of the seats", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    /** The number of seats available in the vehicle. */
    @NotNull
    @Min(1)
    @Schema(description = "Number of seats", example = "5", minimum = "1")
    private Integer quantity;
}
