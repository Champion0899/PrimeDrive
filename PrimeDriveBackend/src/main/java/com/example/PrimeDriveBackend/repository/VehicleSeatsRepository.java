package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleSeats;

@Repository
public interface VehicleSeatsRepository extends JpaRepository<VehicleSeats, String> {
}
