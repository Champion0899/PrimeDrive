package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeDriveBackend.model.VehicleColors;

public interface VehicleColorsRepository extends JpaRepository<VehicleColors, String> {
    Optional<VehicleColors> findById(String id);
}
