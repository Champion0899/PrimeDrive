package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.service.VehicleHoldingsService;

/**
 * Mapper class for converting between VehicleBrands entities and their DTO representations.
 *
 * Provides mapping logic to convert full brand data to DTOs and vice versa.
 * Uses VehicleHoldingsService to resolve holding references during entity construction.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleBrandsMapper {
    private final VehicleHoldingsService vehicleHoldingsService;

    public VehicleBrandsMapper(
            VehicleHoldingsService vehicleHoldingsService) {
        this.vehicleHoldingsService = vehicleHoldingsService;
    }

    /**
     * Converts a VehicleBrands entity to a VehicleBrandsDto.
     *
     * @param vehicleBrands The entity to convert.
     * @return A DTO containing the brand's information.
     */
    public VehicleBrandsDto toDto(VehicleBrands vehicleBrands) {
        VehicleBrandsDto dto = new VehicleBrandsDto();
        dto.setId(vehicleBrands.getId());
        dto.setName(vehicleBrands.getName());
        dto.setFounding(vehicleBrands.getFounding());
        dto.setLogo(vehicleBrands.getLogo());
        dto.setHoldingId(vehicleBrands.getHolding().getId());
        return dto;
    }

    /**
     * Converts a VehicleBrandsDto to a VehicleBrands entity.
     *
     * Uses VehicleHoldingsService to fetch the related holding entity.
     *
     * @param dto The DTO to convert.
     * @return A VehicleBrands entity constructed from the DTO.
     */
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
