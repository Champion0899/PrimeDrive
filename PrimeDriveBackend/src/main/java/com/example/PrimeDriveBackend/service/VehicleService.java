package com.example.PrimeDriveBackend.service;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.Mapper.VehicleMapper;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
    }

    public VehicleDto getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Fahrzeug nicht gefunden"));
    }

    public VehicleDto saveVehicle(VehicleDto dto) {
        Vehicle vehicle = vehicleMapper.toEntity(dto);
        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }
}
