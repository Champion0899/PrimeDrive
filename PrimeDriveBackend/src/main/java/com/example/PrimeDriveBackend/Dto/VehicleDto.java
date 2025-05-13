package com.example.PrimeDriveBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private UUID id;
    private String image;
    private String brand;
    private String model;
    private Integer yearBuild;
    private Integer mileage;
    private BigDecimal price;
    private String condition;
    private String vehicleHistory;
    private String sellerId;
}
