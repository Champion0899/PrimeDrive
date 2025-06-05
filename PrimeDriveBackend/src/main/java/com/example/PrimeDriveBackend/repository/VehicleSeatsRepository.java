package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PrimeDriveBackend.model.VehicleSeats;

/**
 * Repository interface for managing VehicleSeats entities.
 *
 * Provides CRUD operations for vehicle seating configurations using Spring Data
 * JPA.
 * Enables data persistence and lookup of seating-related specifications.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleSeatsRepository extends JpaRepository<VehicleSeats, String> {
    /**
     * Checks whether a seat configuration is currently in use by any vehicle.
     *
     * @param id the ID of the seat configuration
     * @return true if the configuration is in use, false otherwise
     */
    @Query("SELECT COUNT(v) > 0 FROM VehicleSpecs v WHERE v.seats.id = :id")
    boolean isSeatsInUse(@Param("id") String id);
}
