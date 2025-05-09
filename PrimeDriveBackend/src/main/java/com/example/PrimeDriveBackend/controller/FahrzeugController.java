package com.example.PrimeDriveBackend.controller;

import com.example.PrimeDriveBackend.Dto.FahrzeugDto;
import com.example.PrimeDriveBackend.service.FahrzeugService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/fahrzeuge")
@RequiredArgsConstructor
@Tag(name = "Fahrzeug", description = "Endpoints for managing vehicles")
@SecurityRequirement(name = "bearer")
public class FahrzeugController {
    private final FahrzeugService fahrzeugService;

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieves a list of all vehicles.")
    public List<FahrzeugDto> listAll() {
        return fahrzeugService.getAllFahrzeuge();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID", description = "Retrieves a vehicle by its ID.")
    public FahrzeugDto getById(@PathVariable Long id) {
        return fahrzeugService.getFahrzeugById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle", description = "Creates a new vehicle with the provided details.")
    public FahrzeugDto create(@RequestBody FahrzeugDto dto) {
        return fahrzeugService.saveFahrzeug(dto);
    }
}