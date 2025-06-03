package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleBrands;

/**
 * Repository interface for managing VehicleBrands entities.
 *
 * Extends JpaRepository to provide CRUD operations for vehicle brand data,
 * and includes a method for finding a brand by its ID.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleBrandsRepository extends JpaRepository<VehicleBrands, String> {

    /**
     * Retrieves a vehicle brand by its unique identifier.
     *
     * @param id the ID of the vehicle brand
     * @return an Optional containing the brand if found, or empty if not found
     */
    Optional<VehicleBrands> findById(String id);
}
