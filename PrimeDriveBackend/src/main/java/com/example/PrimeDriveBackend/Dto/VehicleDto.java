package com.example.PrimeDriveBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    @NotNull
    @Schema(description = "Id of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;

    @NotNull
    @Schema(description = "Name of the vehicle", example = "M5")
    private String name;

    @NotNull
    @Schema(description = "Price of the vehicle", example = "50000.00")
    private Double price;

    @NotNull
    @Schema(description = "Year of the build", example = "2020")
    private Integer year;

    @NotNull
    @Schema(description = "Image of the vehicle", example = "https://example.com/image.jpg")
    private String image;

    @NotNull
    @Schema(description = "Mileage of the vehicle", example = "30000")
    private Integer mileage;

    @NotNull
    @Schema(description = "Condition of the vehicle", example = "new")
    private String condition;

    @NotNull
    @Schema(description = "vehicleHistory of the vehicle", example = "No accidents")
    private String vehicleHistory;

    @NotNull
    @Schema(description = "BrandId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String brandsId;

    @NotNull
    @Schema(description = "SpecsId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String specsId;

    @NotNull
    @Schema(description = "TypesId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String typesId;

    @NotNull
    @Schema(description = "ColorsId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String colorsId;

    @NotNull
    @Schema(description = "SellerId of the vehicle", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String sellerId;
}
