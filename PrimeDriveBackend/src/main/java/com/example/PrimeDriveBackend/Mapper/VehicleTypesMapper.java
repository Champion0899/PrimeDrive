package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.model.VehicleTypes;

@Component
public class VehicleTypesMapper {
    public VehicleTypesDto toDto(VehicleTypes vehicleTypes) {
        VehicleTypesDto dto = new VehicleTypesDto();
        dto.setId(vehicleTypes.getId());
        dto.setType(vehicleTypes.getType());
        return dto;
    }

    public VehicleTypes toEntity(VehicleTypesDto dto) {
        VehicleTypes vehicleTypes = new VehicleTypes();
        vehicleTypes.setId(dto.getId());
        vehicleTypes.setType(dto.getType());
        return vehicleTypes;
    }

}
