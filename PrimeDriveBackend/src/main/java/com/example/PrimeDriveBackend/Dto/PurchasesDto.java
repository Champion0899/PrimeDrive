package com.example.PrimeDriveBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a vehicle purchase.
 * 
 * This DTO encapsulates the key information involved in a purchase transaction,
 * including IDs for buyer, seller, and the vehicle.
 *
 * Used to transfer data between the backend and frontend layers.
 * 
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasesDto {

    /** The unique identifier of the purchase. */
    @Schema(required = false, description = "The unique identifier for the purchase.")
    private String id;

    /** The UUID of the user who bought the vehicle. */
    @Schema(required = false, description = "The unique identifier for the buyer.")
    private String buyerId;

    /** The UUID of the user who sold the vehicle. */
    @Schema(required = false, description = "The unique identifier for the seller.")
    private String sellerId;

    /** The UUID of the vehicle involved in the purchase. */
    @Schema(required = false, description = "The unique identifier for the vehicle.")
    private String vehicleId;
}
