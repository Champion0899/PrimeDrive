package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.model.Vehicle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VehicleMapper {

    public VehicleDto toDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setImage(vehicle.getImage());
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setYearBuild(vehicle.getYearBuild());
        dto.setMileage(vehicle.getMileage());
        dto.setPrice(BigDecimal.valueOf(vehicle.getPrice()));
        return dto;
    }

    public Vehicle toEntity(VehicleDto dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setImage(dto.getImage());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setYearBuild(dto.getYearBuild());
        vehicle.setMileage(dto.getMileage());
        vehicle.setPrice(dto.getPrice().toBigInteger().doubleValue());
        return vehicle;
    }
}