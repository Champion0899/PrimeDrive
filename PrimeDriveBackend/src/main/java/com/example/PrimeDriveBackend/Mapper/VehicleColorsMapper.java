package com.example.PrimeDriveBackend.mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.model.VehicleColors;

/**
 * Mapper class for converting between VehicleColors entities and their DTO
 * representations.
 *
 * Provides mapping logic to convert color data to DTOs and vice versa,
 * including ID, name,
 * and hexadecimal color code used for display or filtering purposes.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleColorsMapper {

    /**
     * Converts a VehicleColors entity to a VehicleColorsDto.
     *
     * @param vehicleColors The entity containing color data.
     * @return A DTO with the color's ID, name, and hex code.
     */
    public VehicleColorsDto toDto(VehicleColors vehicleColors) {
        VehicleColorsDto dto = new VehicleColorsDto();
        dto.setId(vehicleColors.getId());
        dto.setName(vehicleColors.getName());
        dto.setHexCode(vehicleColors.getHexCode());
        return dto;
    }

    /**
     * Converts a VehicleColorsDto to a VehicleColors entity.
     *
     * @param dto The DTO to convert.
     * @return A VehicleColors entity with ID, name, and hex code.
     */
    public VehicleColors toEntity(VehicleColorsDto dto) {
        VehicleColors vehicleColors = new VehicleColors();
        vehicleColors.setId(dto.getId());
        vehicleColors.setName(dto.getName());
        vehicleColors.setHexCode(dto.getHexCode());
        return vehicleColors;
    }

}
