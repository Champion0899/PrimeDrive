package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeDriveBackend.model.VehicleTypes;

public interface VehicleTypesRepository extends JpaRepository<VehicleTypes, String> {
    Optional<VehicleTypes> findById(String id);
}
