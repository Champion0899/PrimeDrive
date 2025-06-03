
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
package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeDriveBackend.model.VehicleDoors;

public interface VehicleDoorsRepository extends JpaRepository<VehicleDoors, String> {

}
