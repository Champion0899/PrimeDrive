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
import org.springframework.security.core.context.SecurityContextHolder;

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

    public VehicleDto getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: "
                        + id));
    }

    public VehicleDto saveVehicle(VehicleDto dto) {
        Vehicle vehicle = vehicleMapper.toEntity(dto);
        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    public VehicleDto updateVehicle(String id, VehicleDto dto) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existing.getUsers().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to update this vehicle.");
        }

        Vehicle updatedVehicle = vehicleMapper.toEntity(dto);
        updatedVehicle.setId(existing.getId());

        return vehicleMapper.toDto(vehicleRepository.save(updatedVehicle));
    }

    public void deleteVehicle(String id) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existing.getUsers().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to update this vehicle.");
        }
        vehicleRepository.deleteById(id);
    }
}
