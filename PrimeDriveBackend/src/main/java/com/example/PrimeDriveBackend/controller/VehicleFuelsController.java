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

import com.example.PrimeDriveBackend.Dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.service.VehicleFuelsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_fuels")
@RequiredArgsConstructor
@Tag(name = "Vehicle Fuels", description = "Endpoints for managing vehicle fuels")
@SecurityRequirement(name = "bearer")
public class VehicleFuelsController {
    private final VehicleFuelsService vehicleFuelsService;

    @GetMapping
    @Operation(summary = "Get all vehicle fuel types", description = "Retrieves a list of all vehicle fuel types.")
    public List<VehicleFuelsDto> listAll() {
        return vehicleFuelsService.getFuelTypes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle fuel by ID", description = "Retrieves a vehicle fuel by its ID.")
    public VehicleFuelsDto getById(@PathVariable String id) {
        return vehicleFuelsService.getFuelsById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle fuel", description = "Creates a new vehicle fuel with the provided details.")
    public VehicleFuelsDto create(@RequestBody VehicleFuelsDto dto) {
        return vehicleFuelsService.saveFuels(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a vehicle fuel", description = "Updates the details of an existing vehicle fuel.")
    public VehicleFuelsDto update(@PathVariable String id, @RequestBody VehicleFuelsDto dto) {
        dto.setId(id);
        return vehicleFuelsService.updateFuels(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle fuel", description = "Deletes a vehicle fuel by its ID.")
    public void delete(@PathVariable String id) {
        vehicleFuelsService.deleteFuels(id);
    }
}
