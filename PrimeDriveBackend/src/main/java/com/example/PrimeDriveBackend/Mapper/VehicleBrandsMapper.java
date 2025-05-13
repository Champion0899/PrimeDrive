package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.service.VehicleBrandsService;

public class VehicleBrandsMapper {
    private final VehicleBrandsService vehicleBrandsService;
    private final VehicleHoldingsService vehicleHoldingsService;

    public VehicleBrandsMapper(VehicleBrandsService vehicleBrandsService) {
        this.vehicleBrandsService = vehicleBrandsService;
    }

    public VehicleBrandsDto toDto(VehicleBrands vehicleBrands) {
        VehicleBrandsDto dto = new VehicleBrandsDto();
        dto.setId(vehicleBrands.getId());
        dto.setName(vehicleBrands.getName());
        dto.setFounding(vehicleBrands.getFounding());
        dto.setLogo(vehicleBrands.getLogo());
        dto.setHoldingId(vehicleBrands.getHoldingId().getId());
        return dto;
    }

    public VehicleBrands toEntity(VehicleBrandsDto dto) {
        VehicleBrands vehicleBrands = new VehicleBrands();
        vehicleBrands.setId(dto.getId());
        vehicleBrands.setName(dto.getName());
        vehicleBrands.setFounding(dto.getFounding());
        vehicleBrands.setLogo(dto.getLogo());
        vehicleBrands.setHoldingId(vehicleHoldingsService.getHoldingById(dto.getHoldingId()));
        return vehicleBrands;
    }
}
