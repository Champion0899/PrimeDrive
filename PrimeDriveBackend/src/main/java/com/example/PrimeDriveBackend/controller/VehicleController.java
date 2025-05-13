package com.example.PrimeDriveBackend.controller;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
@Tag(name = "vehicle", description = "Endpoints for managing vehicles")
@SecurityRequirement(name = "bearer")
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieves a list of all vehicles.")
    public List<VehicleDto> listAll() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID", description = "Retrieves a vehicle by its ID.")
    public VehicleDto getById(@PathVariable UUID id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle", description = "Creates a new vehicle with the provided details.")
    public VehicleDto create(@RequestBody VehicleDto dto) {
        return vehicleService.saveVehicle(dto);
    }
}