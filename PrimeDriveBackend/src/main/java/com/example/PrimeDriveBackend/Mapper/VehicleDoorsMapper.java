package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.model.VehicleDoors;

@Component
public class VehicleDoorsMapper {

    public VehicleDoorsDto toDto(VehicleDoors vehicleDoors) {
        VehicleDoorsDto dto = new VehicleDoorsDto();
        dto.setId(vehicleDoors.getId());
        dto.setQuantity(vehicleDoors.getQuantity());
        return dto;
    }

    public VehicleDoors toEntity(VehicleDoorsDto dto) {
        VehicleDoors vehicleDoors = new VehicleDoors();
        vehicleDoors.setId(dto.getId());
        vehicleDoors.setQuantity(dto.getQuantity());
        return vehicleDoors;
    }
}
