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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.PrimeDriveBackend.dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleDoorsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * REST controller providing endpoints to manage vehicle door configurations.
 *
 * This controller allows creation, retrieval, updating, and deletion of vehicle
 * door entries.
 * Only users with ADMIN role are permitted to create, update or delete records.
 * All authenticated users may retrieve data.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_doors")
@RequiredArgsConstructor
@Tag(name = "Vehicle Doors", description = "Endpoints for managing vehicle doors")
public class VehicleDoorsController {
    private final VehicleDoorsService vehicleDoorsService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all vehicle door configurations.
     *
     * Accessible to all authenticated users.
     *
     * @return List of VehicleDoorsDto objects
     */
    @GetMapping
    @Operation(summary = "Get all vehicle doors", description = "Retrieves a list of all vehicle doors. Access: All authenticated roles.")
    public List<VehicleDoorsDto> listAll() {
        return vehicleDoorsService.getAllDoors();
    }

    /**
     * Endpoint to retrieve a specific vehicle door configuration by ID.
     *
     * @param id Unique identifier of the vehicle door configuration
     * @return VehicleDoorsDto with the requested configuration
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle doors by ID", description = "Retrieves a vehicle doors by its ID. Access: All authenticated roles.")
    public VehicleDoorsDto getById(@PathVariable String id) {
        return vehicleDoorsService.getDoorsById(id);
    }

    /**
     * Endpoint to create a new vehicle door configuration.
     *
     * Only accessible by users with ADMIN role.
     *
     * @param dto VehicleDoorsDto containing the configuration to create
     * @return VehicleDoorsDto representing the created configuration
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle doors", description = "Creates a new vehicle doors with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle doors created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleDoorsDto create(@RequestBody VehicleDoorsDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleDoorsService.saveDoors(dto);
    }

    /**
     * Endpoint to update an existing vehicle door configuration.
     *
     * Only accessible by ADMIN users.
     *
     * @param id  Unique identifier of the configuration to update
     * @param dto Updated data for the configuration
     * @return VehicleDoorsDto representing the updated configuration
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle doors", description = "Updates the details of an existing vehicle doors by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle doors updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleDoorsDto update(@PathVariable String id, @RequestBody VehicleDoorsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        dto.setId(id);
        return vehicleDoorsService.updateDoors(id, dto);
    }

    /**
     * Endpoint to delete a vehicle door configuration by ID.
     *
     * Only accessible by ADMIN users.
     *
     * @param id Unique identifier of the configuration to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle doors", description = "Deletes a vehicle doors by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle doors deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleDoorsService.deleteDoors(id);
    }
}
