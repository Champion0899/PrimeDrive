package com.example.PrimeDriveBackend.service;

import com.example.PrimeDriveBackend.exception.EntityInUseException;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.PrimeDriveBackend.repository.VehicleRepository;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.mapper.VehicleSpecsMapper;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.repository.VehicleSpecsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle specification operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle specification data.
 * Includes security checks to ensure operations are performed only by
 * authorized users.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleSpecsService {
    private final VehicleSpecsRepository vehicleSpecsRepository;
    private final VehicleSpecsMapper vehicleSpecsMapper;
    private final VehicleRepository vehicleRepository;

    /**
     * Retrieves all vehicle specifications and maps them to DTOs.
     *
     * @return a list of all vehicle specification DTOs
     */
    public List<VehicleSpecsDto> getAllSpecs() {
        return vehicleSpecsRepository.findAll()
                .stream()
                .map(vehicleSpecsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle specification by its ID and maps it to a DTO.
     *
     * @param id the ID of the specification
     * @return the specification as a DTO
     * @throws NoSuchElementException if the specification is not found
     */
    public VehicleSpecsDto getSpecsById(String id) {
        return vehicleSpecsRepository.findById(id)
                .map(vehicleSpecsMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Specs not found with id: " + id));
    }

    /**
     * Retrieves a vehicle specification by its ID as an entity.
     *
     * @param id the ID of the specification
     * @return the specification entity
     * @throws NoSuchElementException if the specification is not found
     */
    public VehicleSpecs getSpecsByIdEntity(String id) {
        return vehicleSpecsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Specs not found with id: " + id));
    }

    /**
     * Saves a new vehicle specification.
     *
     * @param dto the DTO containing specification data
     * @return the saved specification as a DTO
     */
    public VehicleSpecsDto saveSpecs(VehicleSpecsDto dto) {
        VehicleSpecs vehicleSpecs = vehicleSpecsMapper.toEntity(dto);
        return vehicleSpecsMapper.toDto(vehicleSpecsRepository.save(vehicleSpecs));
    }

    /**
     * Updates an existing vehicle specification by ID, ensuring the current user is
     * authorized.
     *
     * @param id         the ID of the specification to update
     * @param updatedDto the updated specification data
     * @return the updated specification as a DTO
     * @throws NoSuchElementException if the specification or related vehicle is not
     *                                found
     * @throws SecurityException      if the user is not authorized to perform the
     *                                update
     */
    public VehicleSpecsDto updateSpecs(String id, VehicleSpecsDto updatedDto) {
        VehicleSpecs existing = vehicleSpecsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Specs not found with id: " + id));

        Vehicle vehicle = vehicleRepository.findBySpecs_Id(id)
                .orElseThrow(() -> new NoSuchElementException("No vehicle found for given specs ID"));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!vehicle.getUsers().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to update these specs.");
        }

        VehicleSpecs updatedSpecs = vehicleSpecsMapper.toEntity(updatedDto);
        updatedSpecs.setId(existing.getId());

        return vehicleSpecsMapper.toDto(vehicleSpecsRepository.save(updatedSpecs));
    }

    /**
     * Deletes a vehicle specification by ID, ensuring the current user is
     * authorized.
     *
     * @param id the ID of the specification to delete
     * @throws NoSuchElementException if the specification or related vehicle is not
     *                                found
     * @throws SecurityException      if the user is not authorized to perform the
     *                                deletion
     */
    public void deleteSpecs(String id) {
        if (!vehicleSpecsRepository.existsById(id)) {
            throw new NoSuchElementException("Specs not found with id: " + id);
        }

        Vehicle vehicle = vehicleRepository.findBySpecs_Id(id)
                .orElseThrow(() -> new NoSuchElementException("No vehicle found for given specs ID"));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!vehicle.getUsers().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to delete these specs.");
        }

        // Prevent deletion if specs are still in use
        if (vehicle.getSpecs() != null && vehicle.getSpecs().getId().equals(id)) {
            throw new EntityInUseException("Cannot delete specs with id " + id + " because they are currently in use.");
        }

        vehicleSpecsRepository.deleteById(id);
    }
}
