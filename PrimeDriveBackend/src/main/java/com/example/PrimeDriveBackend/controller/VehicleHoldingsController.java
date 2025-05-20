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
import org.springframework.http.HttpStatus;

import com.example.PrimeDriveBackend.Dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.service.VehicleHoldingsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_holdings")
@RequiredArgsConstructor
@Tag(name = "Vehicle Holdings", description = "Endpoints for managing vehicle holdings")
@SecurityRequirement(name = "bearer")
public class VehicleHoldingsController {
    private final VehicleHoldingsService vehicleHoldingsService;

    @GetMapping
    @Operation(summary = "Get all vehicle holdings", description = "Retrieves a list of all vehicle holdings.")
    public List<VehicleHoldingsDto> listAll() {
        return vehicleHoldingsService.getAllHoldings();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle holding by ID", description = "Retrieves a vehicle holdings by its ID.")
    public VehicleHoldingsDto getById(@PathVariable String id) {
        return vehicleHoldingsService.getHoldingById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle holding", description = "Creates a new vehicle holdings with the provided details.")
    public VehicleHoldingsDto create(@RequestBody VehicleHoldingsDto dto) {
        return vehicleHoldingsService.saveHolding(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle holding", description = "Updates an existing vehicle holding.")
    public ResponseEntity<VehicleHoldingsDto> update(@PathVariable String id, @RequestBody VehicleHoldingsDto dto) {
        dto.setId(id);
        VehicleHoldingsDto updated = vehicleHoldingsService.updateHolding(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle holding", description = "Deletes a vehicle holding by its ID.")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        vehicleHoldingsService.deleteHolding(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
