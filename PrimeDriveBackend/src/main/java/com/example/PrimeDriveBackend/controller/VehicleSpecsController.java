package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.PrimeDriveBackend.dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleSpecsService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * REST controller providing endpoints to manage vehicle specifications.
 *
 * This controller supports creating, retrieving, updating, and deleting vehicle
 * specification entries.
 * Only users with SELLER or ADMIN roles are permitted to modify or delete
 * records.
 * All authenticated users may access retrieval endpoints.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_specs")
@RequiredArgsConstructor
@Tag(name = "Vehicle Specs", description = "Endpoints for managing vehicle specs")
public class VehicleSpecsController {
    private final VehicleSpecsService vehicleSpecsService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all vehicle specifications.
     *
     * Accessible to all authenticated users.
     *
     * @return List of VehicleSpecsDto objects
     */
    @GetMapping
    @Operation(summary = "Get all vehicle specs", description = "Retrieves a list of all vehicle specs. Access: All authenticated roles.")
    public List<VehicleSpecsDto> listAll() {
        return vehicleSpecsService.getAllSpecs();
    }

    /**
     * Endpoint to retrieve vehicle specifications by ID.
     *
     * @param id Unique identifier of the specification entry
     * @return VehicleSpecsDto containing the requested specification
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle specs by ID", description = "Retrieves a vehicle specs by its ID. Access: All authenticated roles.")
    public VehicleSpecsDto getById(@PathVariable String id) {
        return vehicleSpecsService.getSpecsById(id);
    }

    /**
     * Endpoint to create a new vehicle specification.
     *
     * Only accessible to SELLER or ADMIN users.
     *
     * @param dto VehicleSpecsDto containing the specification data to be created
     * @return VehicleSpecsDto representing the created specification
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle specs", description = "Creates a new vehicle specs with the provided details. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle specs created successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public VehicleSpecsDto create(@Valid @RequestBody VehicleSpecsDto dto, Authentication authentication) {
        System.out.println("Creating vehicle specs: " + dto);
        authenticationService.checkAuthentication(authentication);
        return vehicleSpecsService.saveSpecs(dto);
    }

    /**
     * Endpoint to update an existing vehicle specification.
     *
     * Only accessible to SELLER or ADMIN users.
     *
     * @param id  Unique identifier of the specification to update
     * @param dto Updated specification data
     * @return VehicleSpecsDto representing the updated specification
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle specs", description = "Updates the vehicle specs with the provided ID. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle specs updated successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public VehicleSpecsDto update(@PathVariable String id, @Valid @RequestBody VehicleSpecsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleSpecsService.updateSpecs(id, dto);
    }

    /**
     * Endpoint to delete a vehicle specification by ID.
     *
     * Only accessible to SELLER or ADMIN users.
     *
     * @param id Unique identifier of the specification to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle specs", description = "Deletes the vehicle specs with the provided ID. Access: SELLER or ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle specs deleted successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only SELLER or ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleSpecsService.deleteSpecs(id);
    }
}
