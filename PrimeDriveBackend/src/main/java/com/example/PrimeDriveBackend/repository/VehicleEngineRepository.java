package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleEngine;

@Repository
public interface VehicleEngineRepository extends JpaRepository<VehicleEngine, String> {

}
