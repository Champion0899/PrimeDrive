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

import com.example.PrimeDriveBackend.Dto.VehicleColorsDto;
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
public class VehicleColorsController {
    private final VehicleColorsService vehicleColorsService;

    @GetMapping
    @Operation(summary = "Get all vehicle colors", description = "Retrieves a list of all vehicle colors. Access: All authenticated roles.")
    public List<VehicleColorsDto> listAll() {
        return vehicleColorsService.getAllColors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle color by ID", description = "Retrieves a vehicle color by its ID. Access: All authenticated roles.")
    public VehicleColorsDto getById(@PathVariable String id) {
        return vehicleColorsService.getColorById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new vehicle color", description = "Creates a new vehicle color with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle color created successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleColorsDto create(@RequestBody VehicleColorsDto dto) {
        return vehicleColorsService.saveColor(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update a vehicle color", description = "Updates an existing vehicle color by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle color updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleColorsDto update(@PathVariable String id, @RequestBody VehicleColorsDto dto) {
        return vehicleColorsService.updateColor(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete a vehicle color", description = "Deletes a vehicle color by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle color deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id) {
        vehicleColorsService.deleteColor(id);
    }
}
