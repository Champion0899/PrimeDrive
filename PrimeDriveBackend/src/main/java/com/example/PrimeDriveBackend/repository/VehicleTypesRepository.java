package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeDriveBackend.model.VehicleTypes;

/**
 * Repository interface for managing VehicleTypes entities.
 *
 * Provides CRUD operations for vehicle type classifications using Spring Data
 * JPA.
 * Includes a method to retrieve a vehicle type by its unique identifier.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public interface VehicleTypesRepository extends JpaRepository<VehicleTypes, String> {
    /**
     * Retrieves a vehicle type by its unique identifier.
     *
     * @param id the ID of the vehicle type
     * @return an Optional containing the type if found, or empty if not found
     */
    Optional<VehicleTypes> findById(String id);
}
