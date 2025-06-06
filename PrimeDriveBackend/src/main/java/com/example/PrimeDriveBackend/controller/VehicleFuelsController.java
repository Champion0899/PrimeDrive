package com.example.PrimeDriveBackend.controller;

/**
 * REST controller providing endpoints to manage vehicle fuel types.
 *
 * This controller handles the creation, retrieval, updating, and deletion of fuel type records.
 * Only users with ADMIN role may create, update or delete fuel entries.
 * All authenticated users may access retrieval endpoints.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */

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

import com.example.PrimeDriveBackend.dto.VehicleFuelsDto;
import jakarta.validation.Valid;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleFuelsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_fuels")
@RequiredArgsConstructor
@Tag(name = "Vehicle Fuels", description = "Endpoints for managing vehicle fuels")
public class VehicleFuelsController {
    private final VehicleFuelsService vehicleFuelsService;
    private final AuthenticationService authenticationService;

    @GetMapping
    @Operation(summary = "Get all vehicle fuel types", description = "Retrieves a list of all vehicle fuel types. Access: All authenticated roles.")
    /**
     * Endpoint to retrieve all vehicle fuel types.
     *
     * Accessible to all authenticated users.
     *
     * @return List of VehicleFuelsDto objects
     */
    public List<VehicleFuelsDto> listAll() {
        return vehicleFuelsService.getFuelTypes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle fuel by ID", description = "Retrieves a vehicle fuel by its ID. Access: All authenticated roles.")
    /**
     * Endpoint to retrieve a specific vehicle fuel type by ID.
     *
     * @param id Unique identifier of the fuel type
     * @return VehicleFuelsDto representing the fuel type
     */
    public VehicleFuelsDto getById(@PathVariable String id) {
        return vehicleFuelsService.getFuelsById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle fuel", description = "Creates a new vehicle fuel with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle fuel created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    /**
     * Endpoint to create a new vehicle fuel type.
     *
     * Only accessible to ADMIN users.
     *
     * @param dto VehicleFuelsDto containing the fuel data to be created
     * @return VehicleFuelsDto representing the created fuel type
     */
    public VehicleFuelsDto create(@Valid @RequestBody VehicleFuelsDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleFuelsService.saveFuels(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update a vehicle fuel", description = "Updates the details of an existing vehicle fuel. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle fuel updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    /**
     * Endpoint to update an existing vehicle fuel type.
     *
     * Only accessible to ADMIN users.
     *
     * @param id  Unique identifier of the fuel type to update
     * @param dto Updated fuel data
     * @return VehicleFuelsDto representing the updated fuel type
     */
    public VehicleFuelsDto update(@PathVariable String id, @Valid @RequestBody VehicleFuelsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication); // Assuming authentication is checked elsewhere
        dto.setId(id);
        return vehicleFuelsService.updateFuels(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete a vehicle fuel", description = "Deletes a vehicle fuel by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle fuel deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    /**
     * Endpoint to delete a vehicle fuel type by ID.
     *
     * Only accessible to ADMIN users.
     *
     * @param id Unique identifier of the fuel type to delete
     */
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleFuelsService.deleteFuels(id);
    }
}
