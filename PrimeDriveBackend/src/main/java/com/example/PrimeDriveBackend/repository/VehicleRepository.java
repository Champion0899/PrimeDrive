package com.example.PrimeDriveBackend.repository;

import com.example.PrimeDriveBackend.model.Vehicle;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findBySpecs_Id(String specsId);
}