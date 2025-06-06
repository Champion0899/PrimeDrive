package com.example.PrimeDriveBackend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleColorsMapper;
import com.example.PrimeDriveBackend.model.VehicleColors;
import com.example.PrimeDriveBackend.repository.VehicleColorsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle color operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle color data.
 * Utilizes a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleColorsService {
    private final VehicleColorsRepository vehicleColorsRepository;
    private final VehicleColorsMapper vehicleColorsMapper;

    /**
     * Retrieves all vehicle colors and maps them to DTOs.
     *
     * @return a list of all vehicle color DTOs
     */
    public List<VehicleColorsDto> getAllColors() {
        return vehicleColorsRepository.findAll()
                .stream()
                .map(vehicleColorsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle color by its ID and maps it to a DTO.
     *
     * @param id the ID of the color
     * @return the color as a DTO
     * @throws NoSuchElementException if the color is not found
     */
    public VehicleColorsDto getColorById(String id) {
        return vehicleColorsRepository.findById(id)
                .map(vehicleColorsMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Color not found with id: " + id));
    }

    /**
     * Retrieves a vehicle color by its ID as an entity.
     *
     * @param id the ID of the color
     * @return the color entity
     * @throws NoSuchElementException if the color is not found
     */
    public VehicleColors getColorByIdEntity(String id) {
        return vehicleColorsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Color not found with id: " + id));
    }

    /**
     * Saves a new vehicle color.
     *
     * @param dto the DTO containing color data
     * @return the saved color as a DTO
     */
    public VehicleColorsDto saveColor(VehicleColorsDto dto) {
        VehicleColors vehicleColors = vehicleColorsMapper.toEntity(dto);
        return vehicleColorsMapper.toDto(vehicleColorsRepository.save(vehicleColors));
    }

    /**
     * Updates an existing vehicle color by ID.
     *
     * @param id  the ID of the color to update
     * @param dto the updated color data
     * @return the updated color as a DTO
     * @throws NoSuchElementException if the color is not found
     */
    public VehicleColorsDto updateColor(String id, VehicleColorsDto dto) {
        VehicleColors existing = vehicleColorsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Color not found with id: " + id));

        VehicleColors updatedVehicleColor = vehicleColorsMapper.toEntity(dto);
        updatedVehicleColor.setId(existing.getId());

        return vehicleColorsMapper.toDto(vehicleColorsRepository.save(existing));
    }

    /**
     * Deletes a vehicle color by ID.
     *
     * @param id the ID of the color to delete
     * @throws NoSuchElementException if the color is not found
     * @throws EntityInUseException if the color is currently in use
     */
    public void deleteColor(String id) {
        if (!vehicleColorsRepository.existsById(id)) {
            throw new NoSuchElementException("Color not found with id: " + id);
        }

        if (vehicleColorsRepository.isColorInUse(id)) {
            throw new EntityInUseException("Cannot delete color with id " + id + " because it is currently in use.");
        }

        vehicleColorsRepository.deleteById(id);
    }
}
