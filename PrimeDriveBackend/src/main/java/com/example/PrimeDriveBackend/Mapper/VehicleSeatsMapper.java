package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.model.VehicleSeats;

@Component
public class VehicleSeatsMapper {

    public VehicleSeatsDto toDto(VehicleSeats vehicleSeats) {
        VehicleSeatsDto dto = new VehicleSeatsDto();
        dto.setId(vehicleSeats.getId());
        dto.setQuantity(vehicleSeats.getQuantity());
        return dto;
    }

    public VehicleSeats toEntity(VehicleSeatsDto dto) {
        VehicleSeats vehicleSeats = new VehicleSeats();
        vehicleSeats.setId(dto.getId());
        vehicleSeats.setQuantity(dto.getQuantity());
        return vehicleSeats;
    }
}
