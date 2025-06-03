package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.model.VehicleHoldings;

@Component
/**
 * Mapper class for converting between VehicleHoldings entities and their DTO representations.
 *
 * Handles the transformation of vehicle holding data, such as company name, founding year,
 * and logo, between entity and DTO formats. Used in controller and service layers.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public class VehicleHoldingsMapper {

    /**
     * Converts a VehicleHoldings entity to a VehicleHoldingsDto.
     *
     * @param vehicleHoldings The entity representing a vehicle holding.
     * @return A DTO containing the holding's data.
     */
    public VehicleHoldingsDto toDto(VehicleHoldings vehicleHoldings) {
        VehicleHoldingsDto dto = new VehicleHoldingsDto();
        dto.setId(vehicleHoldings.getId());
        dto.setName(vehicleHoldings.getName());
        dto.setFounding(vehicleHoldings.getFounding());
        dto.setLogo(vehicleHoldings.getLogo());
        return dto;
    }

    /**
     * Converts a VehicleHoldingsDto to a VehicleHoldings entity.
     *
     * @param dto The DTO containing vehicle holding data.
     * @return A VehicleHoldings entity created from the DTO.
     */
    public VehicleHoldings toEntity(VehicleHoldingsDto dto) {
        VehicleHoldings vehicleHoldings = new VehicleHoldings();
        vehicleHoldings.setId(dto.getId());
        vehicleHoldings.setName(dto.getName());
        vehicleHoldings.setFounding(dto.getFounding());
        vehicleHoldings.setLogo(dto.getLogo());
        return vehicleHoldings;
    }
}
