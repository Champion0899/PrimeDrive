package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.mapper.VehicleFuelsMapper;
import com.example.PrimeDriveBackend.model.VehicleFuels;
import com.example.PrimeDriveBackend.repository.VehicleFuelsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle fuel type operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle fuel data.
 * Uses a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleFuelsService {
    private final VehicleFuelsRepository vehicleFuelsRepository;
    private final VehicleFuelsMapper vehicleFuelsMapper;

    /**
     * Retrieves all available vehicle fuel types and maps them to DTOs.
     *
     * @return a list of all vehicle fuel type DTOs
     */
    public List<VehicleFuelsDto> getFuelTypes() {
        return vehicleFuelsRepository.findAll()
                .stream()
                .map(vehicleFuelsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle fuel type by its ID and maps it to a DTO.
     *
     * @param id the ID of the fuel type
     * @return the fuel type as a DTO
     * @throws RuntimeException if the fuel type is not found
     */
    public VehicleFuelsDto getFuelsById(String id) {
        return vehicleFuelsRepository.findById(id)
                .map(vehicleFuelsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Fuel not found with id: " + id));
    }

    /**
     * Retrieves a vehicle fuel type by its ID as an entity.
     *
     * @param id the ID of the fuel type
     * @return the fuel type entity
     * @throws RuntimeException if the fuel type is not found
     */
    public VehicleFuels getFuelsByIdEntity(String id) {
        return vehicleFuelsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found with id: " + id));
    }

    /**
     * Saves a new vehicle fuel type.
     *
     * @param dto the DTO containing fuel type data
     * @return the saved fuel type as a DTO
     */
    public VehicleFuelsDto saveFuels(VehicleFuelsDto dto) {
        VehicleFuels vehicleFuels = vehicleFuelsMapper.toEntity(dto);
        return vehicleFuelsMapper.toDto(vehicleFuelsRepository.save(vehicleFuels));
    }

    /**
     * Updates an existing vehicle fuel type by ID.
     *
     * @param id  the ID of the fuel type to update
     * @param dto the updated fuel type data
     * @return the updated fuel type as a DTO
     * @throws RuntimeException if the fuel type is not found
     */
    public VehicleFuelsDto updateFuels(String id, VehicleFuelsDto dto) {
        VehicleFuels existing = vehicleFuelsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found with id: " + id));

        VehicleFuels updatedVehicleFuel = vehicleFuelsMapper.toEntity(dto);
        updatedVehicleFuel.setId(existing.getId());

        return vehicleFuelsMapper.toDto(vehicleFuelsRepository.save(existing));
    }

    /**
     * Deletes a vehicle fuel type by ID.
     *
     * @param id the ID of the fuel type to delete
     * @throws RuntimeException if the fuel type is not found or is in use
     */
    public void deleteFuels(String id) {
        VehicleFuels existing = vehicleFuelsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found with id: " + id));

        if (vehicleFuelsRepository.isFuelInUse(id)) {
            throw new RuntimeException("Cannot delete fuel type with id " + id + " because it is currently in use.");
        }

        vehicleFuelsRepository.delete(existing);
    }
}
