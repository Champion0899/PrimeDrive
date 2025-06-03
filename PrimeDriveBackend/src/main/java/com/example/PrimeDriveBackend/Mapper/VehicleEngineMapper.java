package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.model.VehicleEngine;

/**
 * Mapper class for converting between VehicleEngine entities and their DTO representations.
 *
 * Provides transformation logic to map engine configuration data to DTOs and back to entity format.
 * Used within the service and controller layers to separate persistence models from transport models.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleEngineMapper {

    /**
     * Converts a VehicleEngine entity to a VehicleEngineDto.
     *
     * @param vehicleEngine The entity containing engine configuration data.
     * @return A DTO representation of the engine.
     */
    public VehicleEngineDto toDto(VehicleEngine vehicleEngine) {
        VehicleEngineDto dto = new VehicleEngineDto();
        dto.setId(vehicleEngine.getId());
        dto.setEngineType(vehicleEngine.getEngineType());
        return dto;
    }

    /**
     * Converts a VehicleEngineDto to a VehicleEngine entity.
     *
     * @param dto The DTO to convert.
     * @return A VehicleEngine entity created from the DTO values.
     */
    public VehicleEngine toEntity(VehicleEngineDto dto) {
        VehicleEngine vehicleEngine = new VehicleEngine();
        vehicleEngine.setId(dto.getId());
        vehicleEngine.setEngineType(dto.getEngineType());
        return vehicleEngine;
    }
}
