package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleSpecs;

@Repository
public interface VehicleSpecsRepository extends JpaRepository<VehicleSpecs, String> {
    Optional<VehicleSpecs> findById(String id);
}
