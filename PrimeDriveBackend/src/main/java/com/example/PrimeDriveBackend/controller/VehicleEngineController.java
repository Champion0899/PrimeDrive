package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeDriveBackend.Dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.service.VehicleEngineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_engine")
@RequiredArgsConstructor
@Tag(name = "Vehicle Engine", description = "Endpoints for managing vehicle engine")
public class VehicleEngineController {
    private final VehicleEngineService vehicleEngineService;

    @GetMapping
    @Operation(summary = "Get all vehicle engines", description = "Retrieves a list of all vehicle engines. Access: All authenticated roles.")
    public List<VehicleEngineDto> listAll() {
        return vehicleEngineService.getAllEngines();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle engine by ID", description = "Retrieves a vehicle engine by its ID. Access: All authenticated roles.")
    public VehicleEngineDto getById(@PathVariable String id) {
        return vehicleEngineService.getEngineById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle engine", description = "Creates a new vehicle engine with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle engine created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleEngineDto create(@RequestBody VehicleEngineDto dto) {
        return vehicleEngineService.saveEngine(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update a vehicle engine", description = "Updates an existing vehicle engine by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle engine updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleEngineDto update(@PathVariable String id, @RequestBody VehicleEngineDto dto) {
        return vehicleEngineService.updateEngine(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete a vehicle engine", description = "Deletes a vehicle engine by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle engine deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id) {
        vehicleEngineService.deleteEngine(id);
    }
}
