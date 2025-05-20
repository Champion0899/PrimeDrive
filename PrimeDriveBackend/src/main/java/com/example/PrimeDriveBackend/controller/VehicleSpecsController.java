package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeDriveBackend.Dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.service.VehicleSpecsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_specs")
@RequiredArgsConstructor
@Tag(name = "Vehicle Specs", description = "Endpoints for managing vehicle specs")
@SecurityRequirement(name = "bearer")
public class VehicleSpecsController {
    private final VehicleSpecsService vehicleSpecsService;

    @GetMapping
    @Operation(summary = "Get all vehicle specs", description = "Retrieves a list of all vehicle specs.")
    public List<VehicleSpecsDto> listAll() {
        return vehicleSpecsService.getAllSpecs();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle specs by ID", description = "Retrieves a vehicle specs by its ID.")
    public VehicleSpecsDto getById(@PathVariable String id) {
        return vehicleSpecsService.getSpecsById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle specs", description = "Creates a new vehicle specs with the provided details.")
    public VehicleSpecsDto create(@RequestBody VehicleSpecsDto dto) {
        return vehicleSpecsService.saveSpecs(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle specs", description = "Updates the vehicle specs with the provided ID.")
    public VehicleSpecsDto update(@PathVariable String id, @RequestBody VehicleSpecsDto dto) {
        return vehicleSpecsService.updateSpecs(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle specs", description = "Deletes the vehicle specs with the provided ID.")
    public void delete(@PathVariable String id) {
        vehicleSpecsService.deleteSpecs(id);
    }
}
