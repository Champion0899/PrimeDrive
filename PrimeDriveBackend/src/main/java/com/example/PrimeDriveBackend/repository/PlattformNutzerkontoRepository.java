package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;

@Repository
public interface PlattformNutzerkontoRepository extends JpaRepository<PlattformNutzerkonto, Long> {

    boolean existsByUsername(String username);
    
    Optional<PlattformNutzerkonto> findByUsername(String username);
}
