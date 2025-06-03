package com.example.PrimeDriveBackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a purchase transaction between a user and a vehicle.
 *
 * Uses a composite key (userId and vehicleId) to uniquely identify each
 * purchase.
 * Establishes many-to-one relationships to both User and Vehicle entities.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PurchaseId.class)
public class Purchases {

    /** The ID of the user involved in the purchase (part of the composite key). */
    @Id
    private UUID userId;

    /**
     * The ID of the vehicle involved in the purchase (part of the composite key).
     */
    @Id
    private UUID vehicleId;

    /** The user entity associated with this purchase. */
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;

    /** The vehicle entity associated with this purchase. */
    @ManyToOne
    @JoinColumn(name = "vehicleId", insertable = false, updatable = false)
    private Vehicle vehicle;
}
