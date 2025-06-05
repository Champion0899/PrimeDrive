package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PrimeDriveBackend.model.VehicleColors;

/**
 * Repository interface for managing VehicleColors entities.
 *
 * Provides access to vehicle color data using Spring Data JPA.
 * Includes a method to retrieve a color entry by its unique identifier.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public interface VehicleColorsRepository extends JpaRepository<VehicleColors, String> {
    /**
     * Retrieves a vehicle color by its unique identifier.
     *
     * @param id the ID of the vehicle color
     * @return an Optional containing the color if found, or empty if not found
     */
    Optional<VehicleColors> findById(String id);

    /**
     * Checks whether a color is currently in use by any vehicle.
     *
     * @param id the ID of the color
     * @return true if the color is in use, false otherwise
     */
    @Query("SELECT COUNT(v) > 0 FROM Vehicle v WHERE v.colors.id = :id")
    boolean isColorInUse(@Param("id") String id);
}
