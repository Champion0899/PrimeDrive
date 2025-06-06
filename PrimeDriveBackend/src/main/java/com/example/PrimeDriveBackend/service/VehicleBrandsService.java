package com.example.PrimeDriveBackend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleBrandsMapper;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.repository.VehicleBrandsRepository;
import com.example.PrimeDriveBackend.util.ImageValidator;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing vehicle brand operations.
 *
 * Provides business logic for retrieving, creating, updating, and deleting
 * vehicle brands.
 * Uses a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class VehicleBrandsService {
    private final VehicleBrandsRepository vehicleBrandsRepository;
    private final VehicleBrandsMapper vehicleBrandsMapper;
    private final VehicleHoldingsService vehicleHoldingsService;

    /**
     * Retrieves all vehicle brands and maps them to DTOs.
     *
     * @return a list of all vehicle brand DTOs
     */
    public List<VehicleBrandsDto> getAllBrands() {
        return vehicleBrandsRepository.findAll()
                .stream()
                .map(vehicleBrandsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a vehicle brand by its ID and maps it to a DTO.
     *
     * @param id the ID of the brand
     * @return the brand as a DTO
     * @throws NoSuchElementException if the brand is not found
     */
    public VehicleBrandsDto getBrandById(String id) {
        return vehicleBrandsRepository.findById(id)
                .map(vehicleBrandsMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Brand not found with id: " + id));
    }

    /**
     * Retrieves a vehicle brand by its ID as an entity.
     *
     * @param id the ID of the brand
     * @return the brand entity
     * @throws NoSuchElementException if the brand is not found
     */
    public VehicleBrands getBrandByIdEntity(String id) {
        return vehicleBrandsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Brand not found with id: " + id));
    }

    /**
     * Saves a new vehicle brand.
     *
     * @param dto the DTO containing brand data
     * @return the saved brand as a DTO
     */
    public VehicleBrandsDto saveBrand(VehicleBrandsDto dto) {
        ImageValidator.validate(dto.getLogo());
        VehicleBrands vehicleBrands = vehicleBrandsMapper.toEntity(dto);
        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(vehicleBrands));
    }

    /**
     * Updates an existing vehicle brand by ID.
     *
     * @param id  the ID of the brand to update
     * @param dto the updated brand data
     * @return the updated brand as a DTO
     * @throws NoSuchElementException if the brand is not found
     */
    public VehicleBrandsDto updateBrand(String id, VehicleBrandsDto dto) {
        VehicleBrands existing = vehicleBrandsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Brand not found with id: " + id));

        // Update holding if necessary
        if (dto.getHoldingId() != null &&
                (existing.getHolding() == null || !dto.getHoldingId().equals(existing.getHolding().getId()))) {
            existing.setHolding(vehicleHoldingsService.getVehicleHoldingEntityById(dto.getHoldingId()));
        }

        ImageValidator.validate(dto.getLogo());
        VehicleBrands updatedVehicleBrand = vehicleBrandsMapper.toEntity(dto);
        updatedVehicleBrand.setId(existing.getId());

        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(existing));
    }

    /**
     * Deletes a vehicle brand by ID.
     *
     * @param id the ID of the brand to delete
     * @throws NoSuchElementException if the brand is not found
     * @throws EntityInUseException   if the brand is currently in use
     */
    public void deleteBrand(String id) {
        if (!vehicleBrandsRepository.existsById(id)) {
            throw new NoSuchElementException("Brand not found with id: " + id);
        }

        if (vehicleBrandsRepository.isBrandInUse(id)) {
            throw new EntityInUseException("Cannot delete brand with id " + id + " because it is currently in use.");
        }

        vehicleBrandsRepository.deleteById(id);
    }
}
