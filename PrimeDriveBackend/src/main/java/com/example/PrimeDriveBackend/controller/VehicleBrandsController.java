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

import com.example.PrimeDriveBackend.dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleBrandsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * REST controller providing endpoints to manage vehicle brands.
 *
 * This controller allows for creating, updating, retrieving and deleting
 * vehicle brands through HTTP requests. Access to creation, updating, and
 * deletion
 * is restricted to users with ADMIN roles.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_brands")
@RequiredArgsConstructor
@Tag(name = "Vehicle Brands", description = "Endpoints for managing vehicle brands")
public class VehicleBrandsController {
    private final VehicleBrandsService vehicleBrandsService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all available vehicle brands.
     *
     * This endpoint is accessible to all authenticated users and returns
     * a complete list of registered vehicle brands.
     *
     * @return List of VehicleBrandsDto objects representing all vehicle brands
     */
    @GetMapping
    @Operation(summary = "Get all vehicle brands", description = "Retrieves a list of all vehicle brands. Access: All authenticated roles.")
    public List<VehicleBrandsDto> listAll() {
        return vehicleBrandsService.getAllBrands();
    }

    /**
     * Endpoint to retrieve a vehicle brand by its unique ID.
     *
     * Accessible to all authenticated users. Returns the brand data for the
     * specified ID.
     *
     * @param id Unique identifier of the vehicle brand to retrieve
     * @return VehicleBrandsDto object containing brand information
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle brand by ID", description = "Retrieves a vehicle brand by its ID. Access: All authenticated roles.")
    public VehicleBrandsDto getById(@PathVariable String id) {
        return vehicleBrandsService.getBrandById(id);
    }

    /**
     * Endpoint for administrators to create a new vehicle brand.
     *
     * Only accessible by users with ADMIN role. Accepts a VehicleBrandsDto with
     * brand data.
     *
     * @param dto VehicleBrandsDto containing the details of the brand to be created
     * @return VehicleBrandsDto representing the newly created brand
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle brand", description = "Creates a new vehicle brand with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle brand created successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleBrandsDto create(@RequestBody VehicleBrandsDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleBrandsService.saveBrand(dto);
    }

    /**
     * Endpoint for administrators to update an existing vehicle brand by ID.
     *
     * Accepts the ID of the brand and a DTO with updated brand data. Only ADMIN
     * access allowed.
     *
     * @param id  Unique identifier of the brand to update
     * @param dto VehicleBrandsDto with the updated brand information
     * @return VehicleBrandsDto representing the updated brand
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle brand by ID", description = "Updates a vehicle brand with the provided ID and details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle brand updated successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleBrandsDto update(@PathVariable String id, @RequestBody VehicleBrandsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleBrandsService.updateBrand(id, dto);
    }

    /**
     * Endpoint to delete a vehicle brand by its ID.
     *
     * This action is restricted to ADMIN users. Deletes the brand permanently.
     *
     * @param id Unique identifier of the brand to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle brand by ID", description = "Deletes a vehicle brand by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle brand deleted successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleBrandsService.deleteBrand(id);
    }
}
