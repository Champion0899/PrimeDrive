package com.example.PrimeDriveBackend.service;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;
import com.example.PrimeDriveBackend.repository.PlattformNutzerkontoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlattformNutzerkontoService {
    private final PlattformNutzerkontoRepository plattformNutzerkontoRepository;

    public boolean existsByUsername(String username) {
        return plattformNutzerkontoRepository.existsByUsername(username);
    }

    public Optional<PlattformNutzerkonto> findByUsername(String username) {
        return plattformNutzerkontoRepository.findByUsername(username);
    }

    public PlattformNutzerkonto save(PlattformNutzerkonto plattformNutzerkonto) {
        return plattformNutzerkontoRepository.save(plattformNutzerkonto);
    }
}
