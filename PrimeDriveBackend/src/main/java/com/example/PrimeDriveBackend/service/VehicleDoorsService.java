package com.example.PrimeDriveBackend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleDoorsMapper;
import com.example.PrimeDriveBackend.model.VehicleDoors;
import com.example.PrimeDriveBackend.repository.VehicleDoorsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle door configuration operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle door data.
 * Maps between DTO and entity representations using the VehicleDoorsMapper.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleDoorsService {

    private final VehicleDoorsRepository vehicleDoorsRepository;
    private final VehicleDoorsMapper vehicleDoorsMapper;

    /**
     * Retrieves all vehicle door configurations and maps them to DTOs.
     *
     * @return a list of all vehicle door DTOs
     */
    public List<VehicleDoorsDto> getAllDoors() {
        return vehicleDoorsRepository.findAll()
                .stream()
                .map(vehicleDoorsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle door configuration by its ID and maps it to a DTO.
     *
     * @param id the ID of the door configuration
     * @return the door configuration as a DTO
     * @throws NoSuchElementException if the door configuration is not found
     */
    public VehicleDoorsDto getDoorsById(String id) {
        return vehicleDoorsRepository.findById(id)
                .map(vehicleDoorsMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Doors not found with id: " + id));
    }

    /**
     * Retrieves a vehicle door configuration by its ID as an entity.
     *
     * @param id the ID of the door configuration
     * @return the door configuration entity
     * @throws NoSuchElementException if the door configuration is not found
     */
    public VehicleDoors getDoorsByIdEntity(String id) {
        return vehicleDoorsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doors not found with id: " + id));
    }

    /**
     * Saves a new vehicle door configuration.
     *
     * @param dto the DTO containing door configuration data
     * @return the saved door configuration as a DTO
     */
    public VehicleDoorsDto saveDoors(VehicleDoorsDto dto) {
        VehicleDoors vehicleDoors = vehicleDoorsMapper.toEntity(dto);
        return vehicleDoorsMapper.toDto(vehicleDoorsRepository.save(vehicleDoors));
    }

    /**
     * Updates an existing vehicle door configuration by ID.
     *
     * @param id  the ID of the door configuration to update
     * @param dto the updated configuration data
     * @return the updated configuration as a DTO
     * @throws NoSuchElementException if the door configuration is not found
     */
    public VehicleDoorsDto updateDoors(String id, VehicleDoorsDto dto) {
        VehicleDoors existing = vehicleDoorsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doors not found with id: " + id));

        VehicleDoors updatedVehicleDoors = vehicleDoorsMapper.toEntity(dto);
        updatedVehicleDoors.setId(existing.getId());
        return vehicleDoorsMapper.toDto(vehicleDoorsRepository.save(existing));
    }

    /**
     * Deletes a vehicle door configuration by ID.
     *
     * @param id the ID of the configuration to delete
     * @throws NoSuchElementException if the configuration is not found
     * @throws EntityInUseException if the configuration is in use
     */
    public void deleteDoors(String id) {
        VehicleDoors existing = vehicleDoorsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doors not found with id: " + id));

        if (vehicleDoorsRepository.isDoorsInUse(id)) {
            throw new EntityInUseException("Cannot delete door configuration with id " + id + " because it is currently in use.");
        }

        vehicleDoorsRepository.delete(existing);
    }
}
