package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.Mapper.VehicleTypesMapper;
import com.example.PrimeDriveBackend.model.VehicleTypes;
import com.example.PrimeDriveBackend.repository.VehicleTypesRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle type operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle type data.
 * Utilizes a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleTypesService {
    private final VehicleTypesRepository vehicleTypesRepository;
    private final VehicleTypesMapper vehicleTypesMapper;

    /**
     * Retrieves all vehicle types and maps them to DTOs.
     *
     * @return a list of all vehicle type DTOs
     */
    public List<VehicleTypesDto> getAllTypes() {
        return vehicleTypesRepository.findAll()
                .stream()
                .map(vehicleTypesMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle type by its ID and maps it to a DTO.
     *
     * @param id the ID of the vehicle type
     * @return the vehicle type as a DTO
     * @throws RuntimeException if the type is not found
     */
    public VehicleTypesDto getTypeById(String id) {
        return vehicleTypesRepository.findById(id)
                .map(vehicleTypesMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
    }

    /**
     * Retrieves a vehicle type by its ID as an entity.
     *
     * @param id the ID of the vehicle type
     * @return the vehicle type entity
     * @throws RuntimeException if the type is not found
     */
    public VehicleTypes getTypeByIdEntity(String id) {
        return vehicleTypesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
    }

    /**
     * Saves a new vehicle type.
     *
     * @param dto the DTO containing vehicle type data
     * @return the saved vehicle type as a DTO
     */
    public VehicleTypesDto saveType(VehicleTypesDto dto) {
        VehicleTypes vehicleTypes = vehicleTypesMapper.toEntity(dto);
        return vehicleTypesMapper.toDto(vehicleTypesRepository.save(vehicleTypes));
    }

    /**
     * Updates an existing vehicle type by ID.
     *
     * @param id  the ID of the vehicle type to update
     * @param dto the updated vehicle type data
     * @return the updated vehicle type as a DTO
     * @throws RuntimeException if the type is not found
     */
    public VehicleTypesDto updateType(String id, VehicleTypesDto dto) {
        VehicleTypes existing = vehicleTypesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));

        VehicleTypes updatedVehicleType = vehicleTypesMapper.toEntity(dto);
        updatedVehicleType.setId(existing.getId());
        return vehicleTypesMapper.toDto(vehicleTypesRepository.save(updatedVehicleType));
    }

    /**
     * Deletes a vehicle type by ID.
     *
     * @param id the ID of the vehicle type to delete
     * @throws RuntimeException if the type is not found or is in use
     */
    public void deleteType(String id) {
        if (!vehicleTypesRepository.existsById(id)) {
            throw new RuntimeException("Type not found with id: " + id);
        }

        boolean isInUse = vehicleTypesRepository.isTypeInUse(id);
        if (isInUse) {
            throw new RuntimeException("Cannot delete vehicle type with id " + id + " because it is currently in use.");
        }

        vehicleTypesRepository.deleteById(id);
    }
}
