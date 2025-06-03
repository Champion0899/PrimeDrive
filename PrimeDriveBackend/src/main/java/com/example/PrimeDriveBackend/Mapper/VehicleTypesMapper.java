package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.model.VehicleTypes;

/**
 * Mapper class for converting between VehicleTypes entities and their DTO
 * representations.
 *
 * Transforms data representing vehicle classifications (e.g. SUV, Sedan, Coupe)
 * into
 * transport-safe DTOs and back into persistent entities.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleTypesMapper {
    /**
     * Converts a VehicleTypes entity to a VehicleTypesDto.
     *
     * @param vehicleTypes The entity containing the vehicle type information.
     * @return A DTO with the type's ID and name.
     */
    public VehicleTypesDto toDto(VehicleTypes vehicleTypes) {
        VehicleTypesDto dto = new VehicleTypesDto();
        dto.setId(vehicleTypes.getId());
        dto.setType(vehicleTypes.getType());
        return dto;
    }

    /**
     * Converts a VehicleTypesDto to a VehicleTypes entity.
     *
     * @param dto The DTO containing type data.
     * @return A VehicleTypes entity based on the DTO.
     */
    public VehicleTypes toEntity(VehicleTypesDto dto) {
        VehicleTypes vehicleTypes = new VehicleTypes();
        vehicleTypes.setId(dto.getId());
        vehicleTypes.setType(dto.getType());
        return vehicleTypes;
    }

}
