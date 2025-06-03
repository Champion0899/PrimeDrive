package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.Mapper.VehicleEngineMapper;
import com.example.PrimeDriveBackend.model.VehicleEngine;
import com.example.PrimeDriveBackend.repository.VehicleEngineRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle engine operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle engine data.
 * Utilizes a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleEngineService {
    private final VehicleEngineRepository vehicleEngineRepository;
    private final VehicleEngineMapper vehicleEngineMapper;

    /**
     * Retrieves all vehicle engine configurations and maps them to DTOs.
     *
     * @return a list of all vehicle engine DTOs
     */
    public List<VehicleEngineDto> getAllEngines() {
        return vehicleEngineRepository.findAll()
                .stream()
                .map(vehicleEngineMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle engine configuration by its ID and maps it to a DTO.
     *
     * @param id the ID of the engine configuration
     * @return the engine configuration as a DTO
     * @throws RuntimeException if the engine configuration is not found
     */
    public VehicleEngineDto getEngineById(String id) {
        return vehicleEngineRepository.findById(id)
                .map(vehicleEngineMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Engine not found with id: " + id));
    }

    /**
     * Retrieves a vehicle engine configuration by its ID as an entity.
     *
     * @param id the ID of the engine configuration
     * @return the engine configuration entity
     * @throws RuntimeException if the engine configuration is not found
     */
    public VehicleEngine getEngineByIdEntity(String id) {
        return vehicleEngineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Engine not found with id: " + id));
    }

    /**
     * Saves a new vehicle engine configuration.
     *
     * @param dto the DTO containing engine configuration data
     * @return the saved engine configuration as a DTO
     */
    public VehicleEngineDto saveEngine(VehicleEngineDto dto) {
        VehicleEngine vehicleEngine = vehicleEngineMapper.toEntity(dto);
        return vehicleEngineMapper.toDto(vehicleEngineRepository.save(vehicleEngine));
    }

    /**
     * Updates an existing vehicle engine configuration by ID.
     *
     * @param id  the ID of the engine configuration to update
     * @param dto the updated configuration data
     * @return the updated configuration as a DTO
     * @throws RuntimeException if the engine configuration is not found
     */
    public VehicleEngineDto updateEngine(String id, VehicleEngineDto dto) {
        VehicleEngine existing = vehicleEngineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Engine not found with id: " + id));

        VehicleEngine updatedVehicleEngine = vehicleEngineMapper.toEntity(dto);
        updatedVehicleEngine.setId(existing.getId());

        return vehicleEngineMapper.toDto(vehicleEngineRepository.save(updatedVehicleEngine));
    }

    /**
     * Deletes a vehicle engine configuration by ID.
     *
     * @param id the ID of the configuration to delete
     * @throws RuntimeException if the configuration is not found
     */
    public void deleteEngine(String id) {
        if (!vehicleEngineRepository.existsById(id)) {
            throw new RuntimeException("Engine not found with id: " + id);
        }
        vehicleEngineRepository.deleteById(id);
    }
}
