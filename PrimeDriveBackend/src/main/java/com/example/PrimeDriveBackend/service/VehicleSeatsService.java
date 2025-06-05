package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleSeatsMapper;
import com.example.PrimeDriveBackend.model.VehicleSeats;
import com.example.PrimeDriveBackend.repository.VehicleSeatsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle seating configuration operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle seat data.
 * Maps between DTO and entity representations using the VehicleSeatsMapper.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleSeatsService {
    private final VehicleSeatsRepository vehicleSeatsRepository;
    private final VehicleSeatsMapper vehicleSeatsMapper;

    /**
     * Retrieves all vehicle seat configurations and maps them to DTOs.
     *
     * @return a list of all vehicle seat DTOs
     */
    public List<VehicleSeatsDto> getAllSeats() {
        return vehicleSeatsRepository.findAll()
                .stream()
                .map(vehicleSeatsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle seat configuration by its ID and maps it to a DTO.
     *
     * @param id the ID of the seat configuration
     * @return the seat configuration as a DTO
     * @throws RuntimeException if the seat configuration is not found
     */
    public VehicleSeatsDto getSeatsById(String id) {
        return vehicleSeatsRepository.findById(id)
                .map(vehicleSeatsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));
    }

    /**
     * Retrieves a vehicle seat configuration by its ID as an entity.
     *
     * @param id the ID of the seat configuration
     * @return the seat configuration entity
     * @throws RuntimeException if the seat configuration is not found
     */
    public VehicleSeats getSeatsByIdEntity(String id) {
        return vehicleSeatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));
    }

    /**
     * Saves a new vehicle seat configuration.
     *
     * @param dto the DTO containing seat configuration data
     * @return the saved seat configuration as a DTO
     */
    public VehicleSeatsDto saveSeats(VehicleSeatsDto dto) {
        VehicleSeats vehicleSeats = vehicleSeatsMapper.toEntity(dto);
        return vehicleSeatsMapper.toDto(vehicleSeatsRepository.save(vehicleSeats));
    }

    /**
     * Updates an existing vehicle seat configuration by ID.
     *
     * @param id  the ID of the seat configuration to update
     * @param dto the updated configuration data
     * @return the updated configuration as a DTO
     * @throws RuntimeException if the seat configuration is not found
     */
    public VehicleSeatsDto updateSeats(String id, VehicleSeatsDto dto) {
        VehicleSeats existing = vehicleSeatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));

        VehicleSeats updatedVehicleSeats = vehicleSeatsMapper.toEntity(dto);
        updatedVehicleSeats.setId(updatedVehicleSeats.getId());

        return vehicleSeatsMapper.toDto(vehicleSeatsRepository.save(existing));
    }

    /**
     * Deletes a vehicle seat configuration by ID.
     *
     * @param id the ID of the configuration to delete
     * @throws RuntimeException if the configuration is not found
     */
    public void deleteSeats(String id) {
        if (!vehicleSeatsRepository.existsById(id)) {
            throw new RuntimeException("Seats not found with id: " + id);
        }

        if (vehicleSeatsRepository.isSeatsInUse(id)) {
            throw new RuntimeException("Cannot delete seat configuration with id " + id + " because it is currently in use.");
        }

        vehicleSeatsRepository.deleteById(id);
    }
}
