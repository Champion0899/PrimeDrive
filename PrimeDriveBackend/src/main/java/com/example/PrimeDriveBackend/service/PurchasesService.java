package com.example.PrimeDriveBackend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.dto.PurchasesDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.PurchasesMapper;
import com.example.PrimeDriveBackend.model.Purchases;
import com.example.PrimeDriveBackend.repository.PurchasesRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing purchase operations.
 *
 * Provides business logic for retrieving, creating, and deleting purchases.
 * Uses a mapper to convert between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-06
 */
@Service
@RequiredArgsConstructor
public class PurchasesService {

    private final PurchasesRepository purchasesRepository;
    private final PurchasesMapper purchasesMapper;
    private final UserService userService;
    private final VehicleService vehicleService;

    /**
     * Retrieves all purchases and maps them to DTOs.
     *
     * @return a list of all purchase DTOs
     */
    public List<PurchasesDto> getAllPurchases() {
        return purchasesRepository.findAll()
                .stream()
                .map(purchasesMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a purchase by its ID and maps it to a DTO.
     *
     * @param id the ID of the purchase
     * @return the purchase as a DTO
     * @throws NoSuchElementException if the purchase is not found
     */
    public PurchasesDto getPurchaseById(String id) {
        return purchasesRepository.findById(id)
                .map(purchasesMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Purchase not found with id: " + id));
    }

    /**
     * Saves a new purchase.
     *
     * @param dto the DTO containing purchase data
     * @return the saved purchase as a DTO
     */
    public PurchasesDto savePurchase(PurchasesDto dto) {
        Purchases purchase = purchasesMapper.toEntity(dto);

        // Optional: Entities laden, falls nötig
        purchase.setBuyer(userService.getByIdEntity(dto.getBuyerId()));
        purchase.setSeller(userService.getByIdEntity(dto.getSellerId()));
        purchase.setVehicle(vehicleService.getVehicleByIdEntity(dto.getVehicleId()));

        return purchasesMapper.toDto(purchasesRepository.save(purchase));
    }

    /**
     * Deletes a purchase by ID.
     *
     * @param id the ID of the purchase to delete
     * @throws NoSuchElementException if the purchase is not found
     * @throws EntityInUseException   if business logic prohibits deletion
     */
    public void deletePurchase(String id) {
        if (!purchasesRepository.existsById(id)) {
            throw new NoSuchElementException("Purchase not found with id: " + id);
        }

        // Optional Business Check
        // if (purchasesRepository.isInvoiced(id)) {
        // throw new EntityInUseException("Purchase is invoiced and cannot be
        // deleted.");
        // }

        purchasesRepository.deleteById(id);
    }
}