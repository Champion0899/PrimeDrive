package com.example.PrimeDriveBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing a vehicle in the PrimeDrive system.
 *
 * This class encapsulates various attributes of a vehicle including its
 * identity,
 * brand, specifications, condition, and ownership details. It is typically used
 * to transfer vehicle-related data between layers of the application.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    /** The unique identifier of the vehicle. */
    @Schema(description = "Id of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    /** The name or model of the vehicle. */
    @NotNull
    @NotBlank
    @Schema(description = "Name of the vehicle", example = "M5")
    private String name;

    /** The price of the vehicle in the specified currency. */
    @NotNull
    @Min(1)
    @Schema(description = "Price of the vehicle", example = "50000.00")
    private Double price;

    /** The manufacturing year of the vehicle. */
    @NotNull
    @Min(1)
    @Schema(description = "Year of the build", example = "2020")
    private Integer year;

    /** A URL to an image representing the vehicle. */
    @NotNull
    @NotBlank
    @Schema(description = "Image of the vehicle", example = "https://example.com/image.jpg")
    private String image;

    /** The mileage (in kilometers or miles) that the vehicle has been driven. */
    @NotNull
    @Min(0)
    @Schema(description = "Mileage of the vehicle", example = "30000")
    private Integer mileage;

    /** The current condition of the vehicle (e.g., new, used). */
    @NotNull
    @NotBlank
    @Schema(description = "Condition of the vehicle", example = "new")
    private String condition;

    /** A short summary of the vehicle's history, such as accident records. */
    @NotNull
    @NotBlank
    @Schema(description = "vehicleHistory of the vehicle", example = "No accidents")
    private String vehicleHistory;

    /** The identifier linking the vehicle to its brand. */
    @NotNull
    @Schema(description = "BrandId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String brandsId;

    /** The identifier linking the vehicle to its specification details. */
    @NotNull
    @Schema(description = "SpecsId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String specsId;

    /** The identifier linking the vehicle to its type category. */
    @NotNull
    @Schema(description = "TypesId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String typesId;

    /** The identifier for the vehicle's color configuration. */
    @NotNull
    @Schema(description = "ColorsId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String colorsId;

    /** The identifier for the seller who listed the vehicle. */
    @NotNull
    @Schema(description = "SellerId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String sellerId;
}
