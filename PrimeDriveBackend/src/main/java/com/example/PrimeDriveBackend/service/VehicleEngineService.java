package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.Mapper.VehicleEngineMapper;
import com.example.PrimeDriveBackend.model.VehicleEngine;
import com.example.PrimeDriveBackend.repository.VehicleEngineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleEngineService {
    private final VehicleEngineRepository vehicleEngineRepository;
    private final VehicleEngineMapper vehicleEngineMapper;

    public List<VehicleEngineDto> getAllEngines() {
        return vehicleEngineRepository.findAll()
                .stream()
                .map(vehicleEngineMapper::toDto)
                .toList();
    }

    public VehicleEngineDto getEngineById(String id) {
        return vehicleEngineRepository.findById(id)
                .map(vehicleEngineMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Engine not found with id: " + id));
    }

    public VehicleEngine getEngineByIdEntity(String id) {
        return vehicleEngineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Engine not found with id: " + id));
    }

    public VehicleEngineDto saveEngine(VehicleEngineDto dto) {
        VehicleEngine vehicleEngine = vehicleEngineMapper.toEntity(dto);
        return vehicleEngineMapper.toDto(vehicleEngineRepository.save(vehicleEngine));
    }

    public VehicleEngineDto updateEngine(String id, VehicleEngineDto dto) {
        VehicleEngine existing = vehicleEngineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Engine not found with id: " + id));

        VehicleEngine updatedVehicleEngine = vehicleEngineMapper.toEntity(dto);
        updatedVehicleEngine.setId(existing.getId());

        return vehicleEngineMapper.toDto(vehicleEngineRepository.save(updatedVehicleEngine));
    }

    public void deleteEngine(String id) {
        if (!vehicleEngineRepository.existsById(id)) {
            throw new RuntimeException("Engine not found with id: " + id);
        }
        vehicleEngineRepository.deleteById(id);
    }
}
