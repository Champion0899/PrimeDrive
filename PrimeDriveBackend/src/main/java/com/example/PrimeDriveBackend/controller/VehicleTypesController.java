package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

import com.example.PrimeDriveBackend.Dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleTypesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * REST controller providing endpoints to manage vehicle types.
 *
 * This controller allows for creation, retrieval, updating, and deletion of
 * vehicle type records.
 * Only users with ADMIN role are permitted to create, update, or delete
 * entries.
 * All authenticated users can retrieve vehicle types.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_types")
@RequiredArgsConstructor
@Tag(name = "Vehicle Types", description = "Endpoints for managing vehicle types")
public class VehicleTypesController {
    private final VehicleTypesService vehicleTypesService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all vehicle types.
     *
     * Accessible to all authenticated users.
     *
     * @return List of VehicleTypesDto objects
     */
    @GetMapping
    @Operation(summary = "Get all vehicle types", description = "Retrieves a list of all vehicle types. Access: All authenticated roles.")
    public List<VehicleTypesDto> listAll() {
        return vehicleTypesService.getAllTypes();
    }

    /**
     * Endpoint to retrieve a vehicle type by its unique ID.
     *
     * @param id Unique identifier of the vehicle type
     * @return VehicleTypesDto representing the requested type
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle type by ID", description = "Retrieves a vehicle type by its ID. Access: All authenticated roles.")
    public VehicleTypesDto getById(@PathVariable String id) {
        return vehicleTypesService.getTypeById(id);
    }

    /**
     * Endpoint to create a new vehicle type.
     *
     * Only accessible to ADMIN users.
     *
     * @param dto VehicleTypesDto containing the vehicle type data to create
     * @return VehicleTypesDto representing the created vehicle type
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle type", description = "Creates a new vehicle type with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle type created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleTypesDto create(@RequestBody VehicleTypesDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleTypesService.saveType(dto);
    }

    /**
     * Endpoint to update an existing vehicle type.
     *
     * Only accessible to ADMIN users.
     *
     * @param id  Unique identifier of the type to update
     * @param dto Updated vehicle type data
     * @return VehicleTypesDto representing the updated type
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle type", description = "Updates the vehicle type with the given ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle type updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleTypesDto update(@PathVariable String id, @RequestBody VehicleTypesDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        dto.setId(id);
        return vehicleTypesService.updateType(id, dto);
    }

    /**
     * Endpoint to delete a vehicle type by ID.
     *
     * Only accessible to ADMIN users.
     *
     * @param id Unique identifier of the type to delete
     * @return HTTP 204 if deletion was successful
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle type", description = "Deletes the vehicle type with the specified ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle type deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleTypesService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
