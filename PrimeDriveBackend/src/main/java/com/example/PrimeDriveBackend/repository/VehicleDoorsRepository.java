package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PrimeDriveBackend.model.VehicleDoors;

/**
 * Repository interface for managing VehicleDoors entities.
 *
 * Provides CRUD operations for vehicle door configurations using Spring Data JPA.
 * This repository enables storage, retrieval, and manipulation of door-related data.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public interface VehicleDoorsRepository extends JpaRepository<VehicleDoors, String> {

    /**
     * Checks whether a door configuration is currently in use by any vehicle.
     *
     * @param id the ID of the door configuration
     * @return true if the configuration is in use, false otherwise
     */
    @Query("SELECT COUNT(v) > 0 FROM VehicleSpecs v WHERE v.doors.id = :id")
    boolean isDoorsInUse(@Param("id") String id);
}
