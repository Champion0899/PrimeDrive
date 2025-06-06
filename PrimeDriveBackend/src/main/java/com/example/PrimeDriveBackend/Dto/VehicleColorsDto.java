package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a vehicle color.
 *
 * This class encapsulates information about available vehicle colors including
 * an identifier, a name, and a hexadecimal color code.
 * It is used for transferring color data between client and backend layers.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleColorsDto {
    /** The unique identifier of the vehicle color. */
    @NotNull
    @Schema(description = "Id of the vehicle color", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    /** The name of the color (e.g., Red, Blue). */
    @NotNull
    @Schema(description = "Name of the vehicle color", example = "Red")
    private String name;
    /** The hexadecimal representation of the color (e.g., FF0000 for red). */
    @NotNull
    @Schema(description = "HexCode of the vehicle color", example = "FF0000")
    private String hexCode;
}
