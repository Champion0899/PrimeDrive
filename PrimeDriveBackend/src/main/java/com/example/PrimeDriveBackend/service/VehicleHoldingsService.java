package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleHoldingsMapper;
import com.example.PrimeDriveBackend.model.VehicleHoldings;
import com.example.PrimeDriveBackend.repository.VehicleHoldingsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleHoldingsService {
    private final VehicleHoldingsRepository vehicleHoldingsRepository;
    private final VehicleHoldingsMapper vehicleHoldingsMapper;

    public List<VehicleHoldingsDto> getAllHoldings() {
        return vehicleHoldingsRepository.findAll()
                .stream()
                .map(vehicleHoldingsMapper::toDto)
                .toList();
    }

    public VehicleHoldingsDto getHoldingById(String id) {
        return vehicleHoldingsRepository.findById(id)
                .map(vehicleHoldingsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Holdings not found with id: " + id));
    }

    public VehicleHoldings getVehicleHoldingEntityById(String id) {
        return vehicleHoldingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holdings not found with id: " + id));
    }

    public VehicleHoldingsDto saveHolding(VehicleHoldingsDto dto) {
        VehicleHoldings vehicleHoldings = vehicleHoldingsMapper.toEntity(dto);
        return vehicleHoldingsMapper.toDto(vehicleHoldingsRepository.save(vehicleHoldings));
    }

    public VehicleHoldingsDto updateHolding(String id, VehicleHoldingsDto dto) {
        VehicleHoldings existing = vehicleHoldingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holdings not found with id: " + id));

        VehicleHoldings updatedHolding = vehicleHoldingsMapper.toEntity(dto);
        updatedHolding.setId(existing.getId());
        return vehicleHoldingsMapper.toDto(vehicleHoldingsRepository.save(updatedHolding));
    }

    public void deleteHolding(String id) {
        if (!vehicleHoldingsRepository.existsById(id)) {
            throw new RuntimeException("Holdings not found with id: " + id);
        }
        vehicleHoldingsRepository.deleteById(id);
    }
}
