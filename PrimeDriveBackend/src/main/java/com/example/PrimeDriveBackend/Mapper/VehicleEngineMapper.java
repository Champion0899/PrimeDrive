package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.model.VehicleEngine;

@Component
public class VehicleEngineMapper {

    public VehicleEngineDto toDto(VehicleEngine vehicleEngine) {
        VehicleEngineDto dto = new VehicleEngineDto();
        dto.setId(vehicleEngine.getId());
        dto.setEngineType(vehicleEngine.getEngineType());
        return dto;
    }

    public VehicleEngine toEntity(VehicleEngineDto dto) {
        VehicleEngine vehicleEngine = new VehicleEngine();
        vehicleEngine.setId(dto.getId());
        vehicleEngine.setEngineType(dto.getEngineType());
        return vehicleEngine;
    }
}
