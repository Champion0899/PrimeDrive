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

import com.example.PrimeDriveBackend.Dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.service.VehicleSeatsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehicle_seats")
@RequiredArgsConstructor
@Tag(name = "Vehicle Seats", description = "Endpoints for managing vehicle seats")
@SecurityRequirement(name = "bearer")
public class VehicleSeatsController {
    private final VehicleSeatsService vehicleSeatsService;

    @GetMapping
    @Operation(summary = "Get all vehicle seats", description = "Retrieves a list of all vehicle seats.")
    public List<VehicleSeatsDto> listAll() {
        return vehicleSeatsService.getAllSeats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle seats by ID", description = "Retrieves a vehicle seats by its ID.")
    public VehicleSeatsDto getById(@PathVariable String id) {
        return vehicleSeatsService.getSeatsById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle seats", description = "Creates a new vehicle seats with the provided details.")
    public VehicleSeatsDto create(@RequestBody VehicleSeatsDto dto) {
        return vehicleSeatsService.saveSeats(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle seats", description = "Updates an existing vehicle seats entry by ID.")
    public VehicleSeatsDto update(@PathVariable String id, @RequestBody VehicleSeatsDto dto) {
        dto.setId(id);
        return vehicleSeatsService.updateSeats(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle seats", description = "Deletes a vehicle seats entry by ID.")
    public void delete(@PathVariable String id) {
        vehicleSeatsService.deleteSeats(id);
    }

}
