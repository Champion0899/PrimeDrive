package com.example.PrimeDriveBackend.controller;

import jakarta.validation.Valid;

import com.example.PrimeDriveBackend.dto.VehicleDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller providing endpoints to manage vehicles.
 *
 * This controller offers functionality to create, update, retrieve and delete
 * vehicle records. Only users with SELLER or ADMIN roles are authorized for
 * modifications. All authenticated users may retrieve vehicle data.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
@Tag(name = "Vehicle", description = "Endpoints for managing vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all vehicles.
     *
     * Accessible by all authenticated users. Returns a list of all registered
     * vehicles.
     *
     * @return List of VehicleDto objects representing vehicles
     */
    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieves a list of all vehicles. Access: All authenticated roles.")
    public List<VehicleDto> listAll() {
        return vehicleService.getAllVehicles();
    }

    /**
     * Endpoint to retrieve a vehicle by its unique ID.
     *
     * Accessible by all authenticated users.
     *
     * @param id Unique identifier of the vehicle
     * @return VehicleDto object representing the vehicle
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID", description = "Retrieves a vehicle by its ID. Access: All authenticated roles.")
    public VehicleDto getById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

    /**
     * Endpoint for SELLER or ADMIN users to create a new vehicle.
     *
     * Accepts a VehicleDto object with vehicle details and returns the created
     * vehicle.
     *
     * @param dto VehicleDto containing the new vehicle's data
     * @return VehicleDto representing the newly created vehicle
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle", description = "Creates a new vehicle with the provided details. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public VehicleDto create(@Valid @RequestBody VehicleDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleService.saveVehicle(dto);
    }

    /**
     * Endpoint for SELLER or ADMIN users to update an existing vehicle.
     *
     * Accepts the vehicle ID and a VehicleDto with updated data.
     *
     * @param id  Unique identifier of the vehicle to update
     * @param dto Updated vehicle data
     * @return VehicleDto representing the updated vehicle
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle by ID", description = "Updates an existing vehicle with the provided details. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public VehicleDto update(@PathVariable String id, @Valid @RequestBody VehicleDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleService.updateVehicle(id, dto);
    }

    /**
     * Endpoint for SELLER or ADMIN users to delete a vehicle by ID.
     *
     * Permanently deletes the vehicle with the given ID.
     *
     * @param id Unique identifier of the vehicle to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle by ID", description = "Deletes a vehicle by its ID. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleService.deleteVehicle(id);
    }
}