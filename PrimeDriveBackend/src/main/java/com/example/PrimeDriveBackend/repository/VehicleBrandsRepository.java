package com.example.PrimeDriveBackend.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.VehicleBrands;

@Repository
public interface VehicleBrandsRepository extends JpaRepository<VehicleBrands, String> {

    Optional<VehicleBrands> findById(String id);
}
