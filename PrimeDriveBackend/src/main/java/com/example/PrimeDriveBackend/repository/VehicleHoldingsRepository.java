package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleHoldings;

/**
 * Repository interface for managing VehicleHoldings entities.
 *
 * Provides CRUD operations for vehicle holding company data using Spring Data
 * JPA.
 * Enables the persistence and retrieval of information about parent companies
 * of vehicle brands.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface VehicleHoldingsRepository extends JpaRepository<VehicleHoldings, String> {

}
