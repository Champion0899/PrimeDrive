package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleColorsMapper;
import com.example.PrimeDriveBackend.model.VehicleColors;
import com.example.PrimeDriveBackend.repository.VehicleColorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleColorsService {
    private final VehicleColorsRepository vehicleColorsRepository;
    private final VehicleColorsMapper vehicleColorsMapper;

    public List<VehicleColorsDto> getAllColors() {
        return vehicleColorsRepository.findAll()
                .stream()
                .map(vehicleColorsMapper::toDto)
                .toList();
    }

    public VehicleColorsDto getBrandById(String id) {
        return vehicleColorsRepository.findById(id)
                .map(vehicleColorsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    public VehicleColors getBrandByIdEntity(String id) {
        return vehicleColorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    public VehicleColorsDto saveBrand(VehicleColorsDto dto) {
        VehicleColors vehicleColors = vehicleColorsMapper.toEntity(dto);
        return vehicleColorsMapper.toDto(vehicleColorsRepository.save(vehicleColors));
    }
}
