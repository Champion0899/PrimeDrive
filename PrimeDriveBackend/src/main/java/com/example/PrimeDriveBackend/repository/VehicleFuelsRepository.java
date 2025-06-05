package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PrimeDriveBackend.model.VehicleFuels;

/**
 * Repository interface for managing VehicleFuels entities.
 *
 * Provides CRUD operations for vehicle fuel type data using Spring Data JPA.
 * Enables interaction with persistent storage of fuel-related information.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleFuelsRepository extends JpaRepository<VehicleFuels, String> {

    /**
     * Checks whether a fuel type is currently in use by any vehicle.
     *
     * @param id the ID of the fuel type
     * @return true if the fuel type is in use, false otherwise
     */
    @Query("SELECT COUNT(v) > 0 FROM VehicleSpecs v WHERE v.fuels.id = :id")
    boolean isFuelInUse(@Param("id") String id);

}
