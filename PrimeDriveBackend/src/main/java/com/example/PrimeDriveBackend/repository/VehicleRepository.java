package com.example.PrimeDriveBackend.repository;

import com.example.PrimeDriveBackend.model.Vehicle;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
}