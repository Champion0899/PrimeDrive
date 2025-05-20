package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleHoldings;

@Repository
public interface VehicleHoldingsRepository extends JpaRepository<VehicleHoldings, String> {

}
