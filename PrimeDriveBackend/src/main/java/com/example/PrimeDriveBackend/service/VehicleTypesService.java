package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.Mapper.VehicleTypesMapper;
import com.example.PrimeDriveBackend.model.VehicleTypes;
import com.example.PrimeDriveBackend.repository.VehicleTypesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleTypesService {
    private final VehicleTypesRepository vehicleTypesRepository;
    private final VehicleTypesMapper vehicleTypesMapper;

    public List<VehicleTypesDto> getAllDoors() {
        return vehicleTypesRepository.findAll()
                .stream()
                .map(vehicleTypesMapper::toDto)
                .toList();
    }

    public VehicleTypesDto getTypesById(String id) {
        return vehicleTypesRepository.findById(id)
                .map(vehicleTypesMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
    }

    public VehicleTypes getSpecsByIdEntity(String id) {
        return vehicleTypesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
    }

    public VehicleTypesDto saveSpecs(VehicleTypesDto dto) {
        VehicleTypes vehicleTypes = vehicleTypesMapper.toEntity(dto);
        return vehicleTypesMapper.toDto(vehicleTypesRepository.save(vehicleTypes));
    }
}
