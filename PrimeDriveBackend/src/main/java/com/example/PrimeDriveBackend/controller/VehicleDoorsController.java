package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeDriveBackend.Dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.service.VehicleDoorsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_doors")
@RequiredArgsConstructor
@Tag(name = "Vehicle Doors", description = "Endpoints for managing vehicle doors")
@SecurityRequirement(name = "bearer")
public class VehicleDoorsController {
    private final VehicleDoorsService vehicleDoorsService;

    @GetMapping
    @Operation(summary = "Get all vehicle doors", description = "Retrieves a list of all vehicle doors.")
    public List<VehicleDoorsDto> listAll() {
        return vehicleDoorsService.getAllDoors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle doors by ID", description = "Retrieves a vehicle doors by its ID.")
    public VehicleDoorsDto getById(@PathVariable String id) {
        return vehicleDoorsService.getDoorsById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle doors", description = "Creates a new vehicle doors with the provided details.")
    public VehicleDoorsDto create(@RequestBody VehicleDoorsDto dto) {
        return vehicleDoorsService.saveDoors(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle doors", description = "Updates the details of an existing vehicle doors by ID.")
    public VehicleDoorsDto update(@PathVariable String id, @RequestBody VehicleDoorsDto dto) {
        dto.setId(id);
        return vehicleDoorsService.updateDoors(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle doors", description = "Deletes a vehicle doors by ID.")
    public void delete(@PathVariable String id) {
        vehicleDoorsService.deleteDoors(id);
    }
}
