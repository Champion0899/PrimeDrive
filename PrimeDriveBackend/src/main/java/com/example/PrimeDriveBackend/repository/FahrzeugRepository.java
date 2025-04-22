package com.example.PrimeDriveBackend.repository;

import com.example.PrimeDriveBackend.model.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FahrzeugRepository extends JpaRepository<Fahrzeug, Long> {}