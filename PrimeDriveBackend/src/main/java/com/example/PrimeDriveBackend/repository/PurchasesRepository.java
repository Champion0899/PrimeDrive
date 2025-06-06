package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.Purchases;

/**
 * Repository interface for performing database operations on Purchases
 * entities.
 *
 * Provides standard CRUD operations and can be extended with custom queries
 * using Spring Data JPA method naming conventions or @Query annotations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-06
 */
@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, String> {
}
