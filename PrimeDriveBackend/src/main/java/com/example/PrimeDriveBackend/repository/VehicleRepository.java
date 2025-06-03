package com.example.PrimeDriveBackend.repository;

import com.example.PrimeDriveBackend.model.Vehicle;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Vehicle entities.
 *
 * Provides standard CRUD operations and includes a custom method
 * for retrieving a vehicle by its associated specification ID.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    /**
     * Retrieves a vehicle based on the ID of its associated specifications.
     *
     * @param specsId the ID of the vehicle specifications
     * @return an Optional containing the vehicle if found, or empty if not found
     */
    Optional<Vehicle> findBySpecs_Id(String specsId);
}