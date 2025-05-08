package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;

@Repository
public interface PlattformNutzerkontoRepository extends JpaRepository<PlattformNutzerkonto, Integer> {

    boolean existsByBenutzername(String benutzername);

    Optional<PlattformNutzerkonto> findById(Integer kontoId);

    Optional<PlattformNutzerkonto> findByBenutzername(String benutzername);
}
