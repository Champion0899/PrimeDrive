package com.example.PrimeDriveBackend.controller;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
@Tag(name = "Vehicle", description = "Endpoints for managing vehicles")
@SecurityRequirement(name = "bearer")
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieves a list of all vehicles. Access: All authenticated roles.")
    public List<VehicleDto> listAll() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID", description = "Retrieves a vehicle by its ID. Access: All authenticated roles.")
    public VehicleDto getById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @Operation(summary = "Create a new vehicle", description = "Creates a new vehicle with the provided details. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle created successfully"),
        @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public VehicleDto create(@RequestBody VehicleDto dto) {
        return vehicleService.saveVehicle(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @Operation(summary = "Update vehicle by ID", description = "Updates an existing vehicle with the provided details. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
        @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public VehicleDto update(@PathVariable String id, @RequestBody VehicleDto dto) {
        return vehicleService.updateVehicle(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @Operation(summary = "Delete vehicle by ID", description = "Deletes a vehicle by its ID. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle deleted successfully"),
        @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public void delete(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
    }
}