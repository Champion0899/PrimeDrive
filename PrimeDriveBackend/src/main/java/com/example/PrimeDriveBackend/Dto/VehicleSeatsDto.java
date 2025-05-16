package com.example.PrimeDriveBackend.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSeatsDto {
    @NotNull
    @Schema(description = "Id of the seats", example = "48d183d9-5658-4488-984e-8801967850e9")
    private String id;
    @NotNull
    @Schema(description = "Number of seats", example = "5")
    private Number quantity;
}
