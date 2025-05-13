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
    private final VehicleMapper vehcicleMapper;

    public List<VehicleDto> getAllFahrzeuge() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehcicleMapper::toDto)
                .collect(Collectors.toList());
    }

    public VehicleDto getFahrzeugById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehcicleMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Fahrzeug nicht gefunden"));
    }

    public VehicleDto saveFahrzeug(VehicleDto dto) {
        Vehicle vehicle = vehcicleMapper.toEntity(dto);
        return vehcicleMapper.toDto(vehicleRepository.save(vehicle));
    }
}
