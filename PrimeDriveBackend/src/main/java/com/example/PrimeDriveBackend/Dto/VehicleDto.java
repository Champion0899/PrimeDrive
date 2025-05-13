package com.example.PrimeDriveBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Long id;
    private String image;
    private String marke;
    private String modell;
    private Integer baujahr;
    private Integer kilometerstand;
    private BigDecimal preis;
}
