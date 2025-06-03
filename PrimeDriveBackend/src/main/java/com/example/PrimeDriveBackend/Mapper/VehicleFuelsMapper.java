package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.model.VehicleFuels;

/**
 * Mapper class for converting between VehicleFuels entities and their DTO representations.
 *
 * Provides logic to convert fuel type data to a DTO and transform DTOs back into entity form.
 * Used for handling fuel-related information within the application.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleFuelsMapper {

    /**
     * Converts a VehicleFuels entity to a VehicleFuelsDto.
     *
     * @param vehicleFuel The entity representing the fuel configuration.
     * @return A DTO containing fuel ID and type.
     */
    public VehicleFuelsDto toDto(VehicleFuels vehicleFuel) {
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setId(vehicleFuel.getId());
        dto.setFuelType(vehicleFuel.getFuelType());
        return dto;
    }

    /**
     * Converts a VehicleFuelsDto to a VehicleFuels entity.
     *
     * @param dto The DTO containing fuel data.
     * @return A VehicleFuels entity based on the DTO values.
     */
    public VehicleFuels toEntity(VehicleFuelsDto dto) {
        VehicleFuels vehicleFuel = new VehicleFuels();
        vehicleFuel.setId(dto.getId());
        vehicleFuel.setFuelType(dto.getFuelType());
        return vehicleFuel;
    }
}
