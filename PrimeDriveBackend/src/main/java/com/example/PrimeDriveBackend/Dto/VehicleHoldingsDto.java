package com.example.PrimeDriveBackend.Dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleHoldingsDto {
    @NotNull
    @Schema(description = "Id of the vehicle holding", example = "48d183d9-5658-4488-984e-8801967850e9")
    String id;

    @NotNull
    @Schema(description = "Name of the vehicle holding", example = "BMW")
    String name;

    @NotNull
    @Schema(description = "Founding date of the vehicle holding", example = "2020")
    Integer founding;

    @NotNull
    @Schema(description = "Logo of the vehicle holding", example = "https://example.com/logo.jpg")
    String logo;
}
