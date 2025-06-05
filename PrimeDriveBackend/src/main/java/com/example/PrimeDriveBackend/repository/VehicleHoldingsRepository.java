package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Checks whether a holding is currently in use by any brand.
     *
     * @param id the ID of the holding
     * @return true if the holding is in use, false otherwise
     */
    @Query("SELECT COUNT(vb) > 0 FROM VehicleBrands vb WHERE vb.holding.id = :id")
    boolean isHoldingInUse(@Param("id") String id);

}
