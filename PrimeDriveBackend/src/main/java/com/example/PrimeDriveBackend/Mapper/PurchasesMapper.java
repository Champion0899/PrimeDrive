package com.example.PrimeDriveBackend.mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.dto.PurchasesDto;
import com.example.PrimeDriveBackend.model.Purchases;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.model.Vehicle;

/**
 * Mapper class for converting between Purchases entity and PurchasesDto.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-06
 */
@Component
public class PurchasesMapper {

    /**
     * Converts a Purchases entity to its corresponding DTO.
     *
     * @param purchases The Purchases entity to convert.
     * @return A PurchasesDto representing the entity.
     */
    public PurchasesDto toDto(Purchases purchases) {
        return new PurchasesDto(
                purchases.getId(),
                purchases.getBuyer().getId(),
                purchases.getSeller().getId(),
                purchases.getVehicle().getId());
    }

    /**
     * Converts a PurchasesDto to a Purchases entity.
     *
     * @param dto The PurchasesDto to convert.
     * @return A Purchases entity with only IDs mapped (buyer, seller, vehicle must
     *         be resolved externally).
     */
    public Purchases toEntity(PurchasesDto dto) {
        Purchases purchase = new Purchases();
        purchase.setId(dto.getId());

        Users buyer = new Users();
        buyer.setId(dto.getBuyerId());
        purchase.setBuyer(buyer);

        Users seller = new Users();
        seller.setId(dto.getSellerId());
        purchase.setSeller(seller);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getVehicleId());
        purchase.setVehicle(vehicle);

        return purchase;
    }
}
