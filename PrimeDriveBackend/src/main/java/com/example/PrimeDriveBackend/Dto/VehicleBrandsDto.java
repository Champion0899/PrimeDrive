package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleBrandsDto {
    @NotNull
    @Schema(description = "Id of the vehicle brand", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    @NotNull
    @Schema(description = "Name of the vehicle brand", example = "BMW")
    private String name;
    @NotNull
    @Schema(description = "Founding date of the vehicle brand", example = "2020")
    private Integer founding;
    @NotNull
    @Schema(description = "Logo of the vehicle brand", example = "https://example.com/logo.jpg")
    private String logo;
    @NotNull
    @Schema(description = "Holding ID of the vehicle brand", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String holdingId;
}
