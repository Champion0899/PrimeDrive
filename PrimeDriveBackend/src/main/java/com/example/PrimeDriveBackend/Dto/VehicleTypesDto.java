package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypesDto {
    @NotNull
    @Schema(description = "Id of vehicle types", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    @NotNull
    @Schema(description = "Type of vehicle", example = "SUV")
    private String type;
}
