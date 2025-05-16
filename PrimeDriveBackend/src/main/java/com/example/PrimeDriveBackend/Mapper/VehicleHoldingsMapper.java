package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.model.VehicleHoldings;

@Component
public class VehicleHoldingsMapper {

    public VehicleHoldingsDto toDto(VehicleHoldings vehicleHoldings) {
        VehicleHoldingsDto dto = new VehicleHoldingsDto();
        dto.setId(vehicleHoldings.getId());
        dto.setName(vehicleHoldings.getName());
        dto.setFounding(vehicleHoldings.getFounding());
        dto.setLogo(vehicleHoldings.getLogo());
        return dto;
    }

    public VehicleHoldings toEntity(VehicleHoldingsDto dto) {
        VehicleHoldings vehicleHoldings = new VehicleHoldings();
        vehicleHoldings.setId(dto.getId());
        vehicleHoldings.setName(dto.getName());
        vehicleHoldings.setFounding(dto.getFounding());
        vehicleHoldings.setLogo(dto.getLogo());
        return vehicleHoldings;
    }
}
