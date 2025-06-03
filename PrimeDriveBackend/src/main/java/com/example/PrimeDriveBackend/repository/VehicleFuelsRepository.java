package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
