package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleBrandsMapper;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.repository.VehicleBrandsRepository;

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
     * @throws RuntimeException if the brand is not found
     */
    public VehicleBrandsDto getBrandById(String id) {
        return vehicleBrandsRepository.findById(id)
                .map(vehicleBrandsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    /**
     * Retrieves a vehicle brand by its ID as an entity.
     *
     * @param id the ID of the brand
     * @return the brand entity
     * @throws RuntimeException if the brand is not found
     */
    public VehicleBrands getBrandByIdEntity(String id) {
        return vehicleBrandsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    /**
     * Saves a new vehicle brand.
     *
     * @param dto the DTO containing brand data
     * @return the saved brand as a DTO
     */
    public VehicleBrandsDto saveBrand(VehicleBrandsDto dto) {
        VehicleBrands vehicleBrands = vehicleBrandsMapper.toEntity(dto);
        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(vehicleBrands));
    }

    /**
     * Updates an existing vehicle brand by ID.
     *
     * @param id  the ID of the brand to update
     * @param dto the updated brand data
     * @return the updated brand as a DTO
     * @throws RuntimeException if the brand is not found
     */
    public VehicleBrandsDto updateBrand(String id, VehicleBrandsDto dto) {
        VehicleBrands existing = vehicleBrandsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

        VehicleBrands updatedVehicleBrand = vehicleBrandsMapper.toEntity(dto);
        updatedVehicleBrand.setId(existing.getId());

        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(existing));
    }

    /**
     * Deletes a vehicle brand by ID.
     *
     * @param id the ID of the brand to delete
     * @throws RuntimeException if the brand is not found
     */
    public void deleteBrand(String id) {
        if (!vehicleBrandsRepository.existsById(id)) {
            throw new RuntimeException("Brand not found with id: " + id);
        }
        vehicleBrandsRepository.deleteById(id);
    }
}
