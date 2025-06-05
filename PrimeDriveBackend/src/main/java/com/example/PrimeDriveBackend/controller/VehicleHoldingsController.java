
/**
 * REST controller providing endpoints to manage vehicle holdings.
 *
 * This controller enables creation, retrieval, updating, and deletion of vehicle holding records.
 * Only users with ADMIN role are permitted to modify or delete records, while all authenticated
 * users may access retrieval endpoints.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.PrimeDriveBackend.Dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleHoldingsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_holdings")
@RequiredArgsConstructor
@Tag(name = "Vehicle Holdings", description = "Endpoints for managing vehicle holdings")
public class VehicleHoldingsController {
    private final VehicleHoldingsService vehicleHoldingsService;
    private final AuthenticationService authenticationService;

    @GetMapping
    @Operation(summary = "Get all vehicle holdings", description = "Retrieves a list of all vehicle holdings. Access: All authenticated roles.")
    /**
     * Endpoint to retrieve all vehicle holdings.
     *
     * Accessible to all authenticated users.
     *
     * @return List of VehicleHoldingsDto objects
     */
    public List<VehicleHoldingsDto> listAll() {
        return vehicleHoldingsService.getAllHoldings();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle holding by ID", description = "Retrieves a vehicle holdings by its ID. Access: All authenticated roles.")
    /**
     * Endpoint to retrieve a specific vehicle holding by ID.
     *
     * @param id Unique identifier of the vehicle holding
     * @return VehicleHoldingsDto representing the requested record
     */
    public VehicleHoldingsDto getById(@PathVariable String id) {
        return vehicleHoldingsService.getHoldingById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle holding", description = "Creates a new vehicle holdings with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle holding created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    /**
     * Endpoint to create a new vehicle holding entry.
     *
     * Only accessible to users with ADMIN role.
     *
     * @param dto VehicleHoldingsDto containing the data to create
     * @return VehicleHoldingsDto representing the created record
     */
    public VehicleHoldingsDto create(@RequestBody VehicleHoldingsDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return vehicleHoldingsService.saveHolding(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update vehicle holding", description = "Updates an existing vehicle holding. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle holding updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    /**
     * Endpoint to update an existing vehicle holding.
     *
     * Only accessible to ADMIN users.
     *
     * @param id  Unique identifier of the holding to update
     * @param dto Updated holding data
     * @return VehicleHoldingsDto with updated information
     */
    public ResponseEntity<VehicleHoldingsDto> update(@PathVariable String id, @RequestBody VehicleHoldingsDto dto,
            Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        dto.setId(id);
        VehicleHoldingsDto updated = vehicleHoldingsService.updateHolding(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete vehicle holding", description = "Deletes a vehicle holding by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle holding deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    /**
     * Endpoint to delete a vehicle holding by ID.
     *
     * Only accessible to ADMIN users.
     *
     * @param id Unique identifier of the holding to delete
     * @return HTTP 204 if deletion was successful
     */
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        vehicleHoldingsService.deleteHolding(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
