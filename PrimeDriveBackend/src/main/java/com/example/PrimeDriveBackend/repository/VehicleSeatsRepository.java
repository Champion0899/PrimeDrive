package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
