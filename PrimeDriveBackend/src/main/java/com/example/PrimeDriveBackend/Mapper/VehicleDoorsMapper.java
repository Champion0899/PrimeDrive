package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.model.VehicleDoors;

/**
 * Mapper class for converting between VehicleDoors entities and their DTO representations.
 *
 * Provides mapping logic to transform door configuration data to DTOs and back to entities.
 * Used primarily in service and controller layers where DTOs are exchanged.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleDoorsMapper {

    /**
     * Converts a VehicleDoors entity to a VehicleDoorsDto.
     *
     * @param vehicleDoors The entity containing door configuration data.
     * @return A DTO representation of the vehicle doors.
     */
    public VehicleDoorsDto toDto(VehicleDoors vehicleDoors) {
        VehicleDoorsDto dto = new VehicleDoorsDto();
        dto.setId(vehicleDoors.getId());
        dto.setQuantity(vehicleDoors.getQuantity());
        return dto;
    }

    /**
     * Converts a VehicleDoorsDto to a VehicleDoors entity.
     *
     * @param dto The DTO to convert.
     * @return A VehicleDoors entity with the corresponding values.
     */
    public VehicleDoors toEntity(VehicleDoorsDto dto) {
        VehicleDoors vehicleDoors = new VehicleDoors();
        vehicleDoors.setId(dto.getId());
        vehicleDoors.setQuantity(dto.getQuantity());
        return vehicleDoors;
    }
}
