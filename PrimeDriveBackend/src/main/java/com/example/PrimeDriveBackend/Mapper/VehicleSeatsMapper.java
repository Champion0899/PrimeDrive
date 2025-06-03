package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.model.VehicleSeats;

/**
 * Mapper class for converting between VehicleSeats entities and their DTO
 * representations.
 *
 * Provides transformation logic to convert seating configuration data into DTOs
 * and back into
 * entity form, typically for transport and persistence layers.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleSeatsMapper {

    /**
     * Converts a VehicleSeats entity to a VehicleSeatsDto.
     *
     * @param vehicleSeats The entity representing seat configuration.
     * @return A DTO with the seat configuration data.
     */
    public VehicleSeatsDto toDto(VehicleSeats vehicleSeats) {
        VehicleSeatsDto dto = new VehicleSeatsDto();
        dto.setId(vehicleSeats.getId());
        dto.setQuantity(vehicleSeats.getQuantity());
        return dto;
    }

    /**
     * Converts a VehicleSeatsDto to a VehicleSeats entity.
     *
     * @param dto The DTO containing seat configuration.
     * @return A VehicleSeats entity created from the DTO.
     */
    public VehicleSeats toEntity(VehicleSeatsDto dto) {
        VehicleSeats vehicleSeats = new VehicleSeats();
        vehicleSeats.setId(dto.getId());
        vehicleSeats.setQuantity(dto.getQuantity());
        return vehicleSeats;
    }
}
