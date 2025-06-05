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

import com.example.PrimeDriveBackend.Dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleSeatsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * REST controller providing endpoints to manage vehicle seat configurations.
 *
 * This controller handles creation, retrieval, updating, and deletion of
 * vehicle seat records.
 * Only users with ADMIN role are permitted to modify or delete entries.
 * All authenticated users can retrieve seat information.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_seats")
@RequiredArgsConstructor
@Tag(name = "Vehicle Seats", description = "Endpoints for managing vehicle seats")
public class VehicleSeatsController {
    private final VehicleSeatsService vehicleSeatsService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all vehicle seat configurations.
     *
     * Accessible to all authenticated users.
     *
     * @return List of VehicleSeatsDto objects
     */
    @GetMapping
    @Operation(summary = "Get all vehicle seats", description = "Retrieves a list of all vehicle seats. Access: All authenticated roles.")
    public List<VehicleSeatsDto> listAll() {
        return vehicleSeatsService.getAllSeats();
    }

    /**
     * Endpoint to retrieve a vehicle seat configuration by ID.
     *
     * @param id Unique identifier of the seat configuration
     * @return VehicleSeatsDto with the requested configuration
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle seats by ID", description = "Retrieves a vehicle seats by its ID. Access: All authenticated roles.")
    public VehicleSeatsDto getById(@PathVariable String id) {
        return vehicleSeatsService.getSeatsById(id);
    }

    /**
     * Endpoint to create a new vehicle seat configuration.
     *
     * Only accessible to users with ADMIN role.
     *
     * @param dto VehicleSeatsDto containing the configuration to create
     * @return VehicleSeatsDto representing the newly created entry
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle seats", description = "Creates a new vehicle seats with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle seats successfully created."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleSeatsDto create(@RequestBody VehicleSeatsDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleSeatsService.saveSeats(dto);
    }

    /**
     * Endpoint to update an existing vehicle seat configuration.
     *
     * Only accessible to ADMIN users.
     *
     * @param id  Unique identifier of the seat to update
     * @param dto Updated seat data
     * @return VehicleSeatsDto with updated information
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle seats", description = "Updates an existing vehicle seats entry by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle seats successfully updated."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleSeatsDto update(@PathVariable String id, @RequestBody VehicleSeatsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        dto.setId(id);
        return vehicleSeatsService.updateSeats(id, dto);
    }

    /**
     * Endpoint to delete a vehicle seat configuration by ID.
     *
     * Only accessible to ADMIN users.
     *
     * @param id Unique identifier of the seat to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle seats", description = "Deletes a vehicle seats entry by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle seats successfully deleted."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleSeatsService.deleteSeats(id);
    }

}
