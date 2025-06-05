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

import com.example.PrimeDriveBackend.Dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleColorsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_colors")
@RequiredArgsConstructor
@Tag(name = "Vehicle Colors", description = "Endpoints for managing vehicle colors")
/**
 * REST controller providing endpoints to manage vehicle colors.
 *
 * This controller allows for creating, updating, retrieving and deleting
 * vehicle color entries. Only administrators have access to create, update, and
 * delete operations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
public class VehicleColorsController {
    private final VehicleColorsService vehicleColorsService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint to retrieve all available vehicle colors.
     *
     * This endpoint is accessible to all authenticated users and returns
     * a list of registered vehicle colors.
     *
     * @return List of VehicleColorsDto objects
     */
    @GetMapping
    @Operation(summary = "Get all vehicle colors", description = "Retrieves a list of all vehicle colors. Access: All authenticated roles.")
    public List<VehicleColorsDto> listAll() {
        return vehicleColorsService.getAllColors();
    }

    /**
     * Endpoint to retrieve a vehicle color by its unique ID.
     *
     * Accessible to all authenticated users. Returns the color data for the
     * specified ID.
     *
     * @param id Unique identifier of the vehicle color to retrieve
     * @return VehicleColorsDto object representing the color
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle color by ID", description = "Retrieves a vehicle color by its ID. Access: All authenticated roles.")
    public VehicleColorsDto getById(@PathVariable String id) {
        return vehicleColorsService.getColorById(id);
    }

    /**
     * Endpoint for administrators to create a new vehicle color.
     *
     * Only accessible by users with ADMIN role. Accepts a VehicleColorsDto with
     * color data.
     *
     * @param dto VehicleColorsDto containing the details of the color to be created
     * @return VehicleColorsDto representing the newly created color
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle color", description = "Creates a new vehicle color with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle color created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleColorsDto create(@RequestBody VehicleColorsDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleColorsService.saveColor(dto);
    }

    /**
     * Endpoint for administrators to update an existing vehicle color by ID.
     *
     * Accepts the ID of the color and a DTO with updated data. Only ADMIN access
     * allowed.
     *
     * @param id  Unique identifier of the color to update
     * @param dto VehicleColorsDto with the updated color information
     * @return VehicleColorsDto representing the updated color
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update a vehicle color", description = "Updates an existing vehicle color by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle color updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleColorsDto update(@PathVariable String id, @RequestBody VehicleColorsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleColorsService.updateColor(id, dto);
    }

    /**
     * Endpoint to delete a vehicle color by its ID.
     *
     * This action is restricted to ADMIN users. Deletes the color permanently.
     *
     * @param id Unique identifier of the color to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete a vehicle color", description = "Deletes a vehicle color by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle color deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleColorsService.deleteColor(id);
    }
}
