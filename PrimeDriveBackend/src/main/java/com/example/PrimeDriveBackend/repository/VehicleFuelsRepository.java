package com.example.PrimeDriveBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleFuels;

@Repository
public interface VehicleFuelsRepository extends JpaRepository<VehicleFuels, String> {

}
