package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeDriveBackend.Dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.service.VehicleTypesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_types")
@RequiredArgsConstructor
@Tag(name = "Vehicle Types", description = "Endpoints for managing vehicle types")
@SecurityRequirement(name = "bearer")
public class VehicleTypesController {
    private final VehicleTypesService vehicleTypesService;

    @GetMapping
    @Operation(summary = "Get all vehicle types", description = "Retrieves a list of all vehicle types.")
    public List<VehicleTypesDto> listAll() {
        return vehicleTypesService.getAllTypes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle type by ID", description = "Retrieves a vehicle type by its ID.")
    public VehicleTypesDto getById(@PathVariable String id) {
        return vehicleTypesService.getTypeById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle type", description = "Creates a new vehicle type with the provided details.")
    public VehicleTypesDto create(@RequestBody VehicleTypesDto dto) {
        return vehicleTypesService.saveType(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle type", description = "Updates the vehicle type with the given ID.")
    public VehicleTypesDto update(@PathVariable String id, @RequestBody VehicleTypesDto dto) {
        dto.setId(id);
        return vehicleTypesService.updateType(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle type", description = "Deletes the vehicle type with the specified ID.")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        vehicleTypesService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
