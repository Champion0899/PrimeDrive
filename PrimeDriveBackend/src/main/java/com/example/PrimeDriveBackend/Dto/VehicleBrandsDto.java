package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a vehicle brand.
 *
 * This class encapsulates data about a vehicle brand including its name,
 * founding year, logo URL, and associated holding ID. Used for transferring
 * vehicle brand data between the backend layers and client-facing APIs.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleBrandsDto {
    /** The unique identifier of the vehicle brand. */
    @NotNull
    @Schema(description = "Id of the vehicle brand", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    /** The name of the vehicle brand (e.g., BMW, Toyota). */
    @NotNull
    @Schema(description = "Name of the vehicle brand", example = "BMW")
    private String name;
    /** The year the vehicle brand was founded. */
    @NotNull
    @Schema(description = "Founding date of the vehicle brand", example = "2020")
    private Integer founding;
    /** The URL pointing to the brand's logo image. */
    @NotNull
    @Schema(description = "Logo of the vehicle brand", example = "https://example.com/logo.jpg")
    private String logo;
    /** The ID referencing the holding company this brand belongs to. */
    @NotNull
    @Schema(description = "Holding ID of the vehicle brand", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String holdingId;
}
