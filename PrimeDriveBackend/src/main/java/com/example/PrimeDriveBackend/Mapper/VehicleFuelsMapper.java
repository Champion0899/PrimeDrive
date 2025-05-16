package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.model.VehicleFuels;

@Component
public class VehicleFuelsMapper {

    public VehicleFuelsDto toDto(VehicleFuels vehicleFuel) {
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setId(vehicleFuel.getId());
        dto.setFuelType(vehicleFuel.getFuelType());
        return dto;
    }

    public VehicleFuels toEntity(VehicleFuelsDto dto) {
        VehicleFuels vehicleFuel = new VehicleFuels();
        vehicleFuel.setId(dto.getId());
        vehicleFuel.setFuelType(dto.getFuelType());
        return vehicleFuel;
    }
}
