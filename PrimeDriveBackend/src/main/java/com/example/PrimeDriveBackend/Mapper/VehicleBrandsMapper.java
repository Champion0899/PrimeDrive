package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.service.VehicleHoldingsService;

@Component
public class VehicleBrandsMapper {
    private final VehicleHoldingsService vehicleHoldingsService;

    public VehicleBrandsMapper(
            VehicleHoldingsService vehicleHoldingsService) {
        this.vehicleHoldingsService = vehicleHoldingsService;
    }

    public VehicleBrandsDto toDto(VehicleBrands vehicleBrands) {
        VehicleBrandsDto dto = new VehicleBrandsDto();
        dto.setId(vehicleBrands.getId());
        dto.setName(vehicleBrands.getName());
        dto.setFounding(vehicleBrands.getFounding());
        dto.setLogo(vehicleBrands.getLogo());
        dto.setHoldingId(vehicleBrands.getHolding().getId());
        return dto;
    }

    public VehicleBrands toEntity(VehicleBrandsDto dto) {
        VehicleBrands vehicleBrands = new VehicleBrands();
        vehicleBrands.setId(dto.getId());
        vehicleBrands.setName(dto.getName());
        vehicleBrands.setFounding(dto.getFounding());
        vehicleBrands.setLogo(dto.getLogo());
        vehicleBrands.setHolding(vehicleHoldingsService.getVehicleHoldingEntityById(dto.getHoldingId()));
        return vehicleBrands;
    }
}
