package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Checks whether a vehicle type is currently in use by any vehicle.
     *
     * @param id the ID of the vehicle type
     * @return true if the type is in use, false otherwise
     */
    @Query("SELECT COUNT(v) > 0 FROM Vehicle v WHERE v.types.id = :id")
    boolean isTypeInUse(@Param("id") String id);
}
