package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleFuelsMapper;
import com.example.PrimeDriveBackend.model.VehicleFuels;
import com.example.PrimeDriveBackend.repository.VehicleFuelsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleFuelsService {
    private final VehicleFuelsRepository vehicleFuelsRepository;
    private final VehicleFuelsMapper vehicleFuelsMapper;

    public List<VehicleFuelsDto> getFuelTypes() {
        return vehicleFuelsRepository.findAll()
                .stream()
                .map(vehicleFuelsMapper::toDto)
                .toList();
    }

    public VehicleFuelsDto getFuelsById(String id) {
        return vehicleFuelsRepository.findById(id)
                .map(vehicleFuelsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Fuel not found with id: " + id));
    }

    public VehicleFuels getFuelsByIdEntity(String id) {
        return vehicleFuelsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found with id: " + id));
    }

    public VehicleFuelsDto saveFuels(VehicleFuelsDto dto) {
        VehicleFuels vehicleFuels = vehicleFuelsMapper.toEntity(dto);
        return vehicleFuelsMapper.toDto(vehicleFuelsRepository.save(vehicleFuels));
    }
}
