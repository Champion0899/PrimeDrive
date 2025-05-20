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

import com.example.PrimeDriveBackend.Dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.service.VehicleBrandsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_brands")
@RequiredArgsConstructor
@Tag(name = "Vehicle Brands", description = "Endpoints for managing vehicle brands")
@SecurityRequirement(name = "bearer")
public class VehicleBrandsController {
    private final VehicleBrandsService vehicleBrandsService;

    @GetMapping
    @Operation(summary = "Get all vehicle brands", description = "Retrieves a list of all vehicle brands. Access: All authenticated roles.")
    public List<VehicleBrandsDto> listAll() {
        return vehicleBrandsService.getAllBrands();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle brand by ID", description = "Retrieves a vehicle brand by its ID. Access: All authenticated roles.")
    public VehicleBrandsDto getById(@PathVariable String id) {
        return vehicleBrandsService.getBrandById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new vehicle brand", description = "Creates a new vehicle brand with the provided details. Access: ADMIN only.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle brand created successfully."),
        @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleBrandsDto create(@RequestBody VehicleBrandsDto dto) {
        return vehicleBrandsService.saveBrand(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update vehicle brand by ID", description = "Updates a vehicle brand with the provided ID and details. Access: ADMIN only.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle brand updated successfully."),
        @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public VehicleBrandsDto update(@PathVariable String id, @RequestBody VehicleBrandsDto dto) {
        return vehicleBrandsService.updateBrand(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete vehicle brand by ID", description = "Deletes a vehicle brand by its ID. Access: ADMIN only.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle brand deleted successfully."),
        @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id) {
        vehicleBrandsService.deleteBrand(id);
    }
}
