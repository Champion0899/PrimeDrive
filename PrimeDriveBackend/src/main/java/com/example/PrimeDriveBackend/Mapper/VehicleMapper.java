package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.model.Vehicle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VehicleMapper {

    public VehicleDto toDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setImage(vehicle.getImage());
        dto.setId(Long.valueOf(vehicle.getFahrzeugId()));
        dto.setMarke(vehicle.getMarke());
        dto.setModell(vehicle.getModell());
        dto.setBaujahr(vehicle.getBaujahr());
        dto.setKilometerstand(vehicle.getKilometerstand());
        dto.setPreis(BigDecimal.valueOf(vehicle.getPreis()));
        return dto;
    }

    public Vehicle toEntity(VehicleDto dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setFahrzeugId(Math.toIntExact(dto.getId()));
        vehicle.setImage(dto.getImage());
        vehicle.setMarke(dto.getMarke());
        vehicle.setModell(dto.getModell());
        vehicle.setBaujahr(dto.getBaujahr());
        vehicle.setKilometerstand(dto.getKilometerstand());
        vehicle.setPreis(dto.getPreis().toBigInteger().doubleValue());
        return vehicle;
    }
}