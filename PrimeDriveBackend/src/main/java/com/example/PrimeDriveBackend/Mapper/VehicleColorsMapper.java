package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.model.VehicleColors;

@Component
public class VehicleColorsMapper {

    public VehicleColorsDto toDto(VehicleColors vehicleColors) {
        VehicleColorsDto dto = new VehicleColorsDto();
        dto.setId(vehicleColors.getId());
        dto.setName(vehicleColors.getName());
        dto.setHexCode(vehicleColors.getHexCode());
        return dto;
    }

    public VehicleColors toEntity(VehicleColorsDto dto) {
        VehicleColors vehicleColors = new VehicleColors();
        vehicleColors.setId(dto.getId());
        vehicleColors.setName(dto.getName());
        vehicleColors.setHexCode(dto.getHexCode());
        return vehicleColors;
    }

}
