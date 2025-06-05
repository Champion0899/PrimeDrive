package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleEngine;

/**
 * Repository interface for managing VehicleEngine entities.
 *
 * Provides CRUD operations for engine configuration data using Spring Data JPA.
 * Used to persist and retrieve engine types such as V6, Electric, etc.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleEngineRepository extends JpaRepository<VehicleEngine, String> {

    /**
     * Checks whether an engine configuration is currently in use by any vehicle.
     *
     * @param id the ID of the engine
     * @return true if the engine is in use, false otherwise
     */
    @Query("SELECT COUNT(v) > 0 FROM VehicleSpecs v WHERE v.engine.id = :id")
    boolean isEngineInUse(@Param("id") String id);

}
