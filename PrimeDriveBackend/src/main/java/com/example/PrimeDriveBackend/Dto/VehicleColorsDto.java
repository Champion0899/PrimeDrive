package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleColorsDto {
    @NotNull
    @Schema(description = "Id of the vehicle color", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    @NotNull
    @Schema(description = "Name of the vehicle color", example = "Red")
    private String name;
    @NotNull
    @Schema(description = "HexCode of the vehicle color", example = "FF0000")
    private String hexCode;
}
