package com.example.PrimeDriveBackend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.PrimeDriveBackend.dto.PurchasesDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.PurchasesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * REST controller providing endpoints to manage purchases.
 *
 * All endpoints are restricted to authenticated users with ADMIN role.
 * Provides functionality to list, retrieve, create and delete purchases.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-06
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
@Tag(name = "Purchases", description = "Endpoints for managing vehicle purchases")
public class PurchasesController {
    private final PurchasesService purchasesService;
    private final AuthenticationService authenticationService;

    /**
     * Retrieves all purchases.
     *
     * @return list of all purchases
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Get all purchases", description = "Retrieves a list of all purchases. Access: ADMIN only.")
    public List<PurchasesDto> getAll(Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return purchasesService.getAllPurchases();
    }

    /**
     * Retrieves a single purchase by its ID.
     *
     * @param id purchase ID
     * @return purchase DTO
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Get purchase by ID", description = "Retrieves a purchase by its ID. Access: ADMIN only.")
    public PurchasesDto getById(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return purchasesService.getPurchaseById(id);
    }

    /**
     * Creates a new purchase.
     *
     * @param dto the purchase DTO
     * @return saved purchase DTO
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new purchase", description = "Creates a new purchase. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase created successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public PurchasesDto create(@Valid @RequestBody PurchasesDto dto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return purchasesService.savePurchase(dto);
    }

    /**
     * Deletes a purchase by ID.
     *
     * @param id the purchase ID
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete a purchase", description = "Deletes a purchase by ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase deleted successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void delete(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        purchasesService.deletePurchase(id);
    }
}
