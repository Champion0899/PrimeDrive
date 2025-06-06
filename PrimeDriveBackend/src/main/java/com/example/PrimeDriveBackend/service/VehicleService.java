package com.example.PrimeDriveBackend.service;

import com.example.PrimeDriveBackend.util.ImageValidator;

import com.example.PrimeDriveBackend.dto.VehicleDto;
import com.example.PrimeDriveBackend.mapper.VehicleMapper;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.repository.VehicleRepository;
import java.util.NoSuchElementException;
import com.example.PrimeDriveBackend.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Service class for managing vehicle operations.
 *
 * Provides methods to retrieve, create, update, and delete vehicles, while
 * handling access control based on the authenticated user context.
 * Utilizes VehicleMapper for entity-DTO conversion.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    /**
     * Retrieves all vehicles from the database and maps them to DTOs.
     *
     * @return a list of vehicle DTOs
     */
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific vehicle by ID and maps it to a DTO.
     *
     * @param id the ID of the vehicle
     * @return the vehicle as a DTO
     * @throws NoSuchElementException if the vehicle is not found
     */
    public VehicleDto getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Vehicle not found with id: "
                        + id));
    }

    /**
     * Retrieves a vehicle by its ID as an entity.
     *
     * @param id the ID of the vehicle
     * @return the vehicle entity
     * @throws NoSuchElementException if the vehicle is not found
     */
    public Vehicle getVehicleByIdEntity(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Specs not found with id: " + id));
    }

    /**
     * Saves a new vehicle to the database.
     *
     * @param dto the vehicle data to save
     * @return the saved vehicle as a DTO
     */
    public VehicleDto saveVehicle(VehicleDto dto) {
        ImageValidator.validate(dto.getImage());
        Vehicle vehicle = vehicleMapper.toEntity(dto);
        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    /**
     * Updates an existing vehicle by ID, checking ownership via security context.
     *
     * @param id  the ID of the vehicle to update
     * @param dto the updated vehicle data
     * @return the updated vehicle as a DTO
     * @throws NoSuchElementException      if the vehicle is not found
     * @throws UnauthorizedAccessException if the authenticated user is not the
     *                                     vehicle
     *                                     owner
     */
    public VehicleDto updateVehicle(String id, VehicleDto dto) {
        ImageValidator.validate(dto.getImage());
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehicle not found with id: " + id));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existing.getUsers().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You are not authorized to update this vehicle.");
        }

        Vehicle updatedVehicle = vehicleMapper.toEntity(dto);
        updatedVehicle.setId(existing.getId());

        return vehicleMapper.toDto(vehicleRepository.save(updatedVehicle));
    }

    /**
     * Deletes a vehicle by ID, validating ownership.
     *
     * @param id the ID of the vehicle to delete
     * @throws NoSuchElementException      if the vehicle is not found
     * @throws UnauthorizedAccessException if the authenticated user is not the
     *                                     vehicle
     *                                     owner
     */
    public void deleteVehicle(String id) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehicle not found with id: " + id));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existing.getUsers().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You are not authorized to update this vehicle.");
        }
        vehicleRepository.deleteById(id);
    }
}
