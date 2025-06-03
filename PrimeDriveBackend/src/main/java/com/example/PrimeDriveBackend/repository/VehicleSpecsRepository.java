package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleSpecs;

/**
 * Repository interface for managing VehicleSpecs entities.
 *
 * Provides CRUD operations for detailed technical vehicle specifications using
 * Spring Data JPA.
 * Enables persistence and retrieval of specifications like engine, dimensions,
 * and performance.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleSpecsRepository extends JpaRepository<VehicleSpecs, String> {
}
