package com.example.PrimeDriveBackend.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeDriveBackend.model.VehicleSpecs;

public interface VehicleSpecsRepository extends JpaRepository<VehicleSpecs, String> {
    Optional<VehicleSpecs> findById(String id);
}
