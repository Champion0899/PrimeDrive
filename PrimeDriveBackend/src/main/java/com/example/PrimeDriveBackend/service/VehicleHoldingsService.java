package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleHoldingsMapper;
import com.example.PrimeDriveBackend.model.VehicleHoldings;
import com.example.PrimeDriveBackend.repository.VehicleHoldingsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle holding company operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle holding data.
 * Uses a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleHoldingsService {
    private final VehicleHoldingsRepository vehicleHoldingsRepository;
    private final VehicleHoldingsMapper vehicleHoldingsMapper;

    /**
     * Retrieves all vehicle holding companies and maps them to DTOs.
     *
     * @return a list of all vehicle holding DTOs
     */
    public List<VehicleHoldingsDto> getAllHoldings() {
        return vehicleHoldingsRepository.findAll()
                .stream()
                .map(vehicleHoldingsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle holding company by its ID and maps it to a DTO.
     *
     * @param id the ID of the holding
     * @return the holding as a DTO
     * @throws RuntimeException if the holding is not found
     */
    public VehicleHoldingsDto getHoldingById(String id) {
        return vehicleHoldingsRepository.findById(id)
                .map(vehicleHoldingsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Holdings not found with id: " + id));
    }

    /**
     * Retrieves a vehicle holding company by its ID as an entity.
     *
     * @param id the ID of the holding
     * @return the holding entity
     * @throws RuntimeException if the holding is not found
     */
    public VehicleHoldings getVehicleHoldingEntityById(String id) {
        return vehicleHoldingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holdings not found with id: " + id));
    }

    /**
     * Saves a new vehicle holding company.
     *
     * @param dto the DTO containing holding data
     * @return the saved holding as a DTO
     */
    public VehicleHoldingsDto saveHolding(VehicleHoldingsDto dto) {
        VehicleHoldings vehicleHoldings = vehicleHoldingsMapper.toEntity(dto);
        return vehicleHoldingsMapper.toDto(vehicleHoldingsRepository.save(vehicleHoldings));
    }

    /**
     * Updates an existing vehicle holding company by ID.
     *
     * @param id  the ID of the holding to update
     * @param dto the updated holding data
     * @return the updated holding as a DTO
     * @throws RuntimeException if the holding is not found
     */
    public VehicleHoldingsDto updateHolding(String id, VehicleHoldingsDto dto) {
        VehicleHoldings existing = vehicleHoldingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holdings not found with id: " + id));

        VehicleHoldings updatedHolding = vehicleHoldingsMapper.toEntity(dto);
        updatedHolding.setId(existing.getId());
        return vehicleHoldingsMapper.toDto(vehicleHoldingsRepository.save(updatedHolding));
    }

    /**
     * Deletes a vehicle holding company by ID.
     *
     * @param id the ID of the holding to delete
     * @throws RuntimeException if the holding is not found
     */
    public void deleteHolding(String id) {
        if (!vehicleHoldingsRepository.existsById(id)) {
            throw new RuntimeException("Holdings not found with id: " + id);
        }
        vehicleHoldingsRepository.deleteById(id);
    }
}
