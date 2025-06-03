package com.example.PrimeDriveBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Composite primary key class for the Purchase entity.
 *
 * Combines the identifiers of the user and the vehicle into a single compound
 * key,
 * used to uniquely identify a purchase record.
 *
 * Author: Jamie Schüpbach & Lorin Baumann
 * Version: 1.0
 * Date: 2025-06-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseId implements Serializable {
    /** The unique identifier of the user who made the purchase. */
    private UUID userId;
    /** The unique identifier of the purchased vehicle. */
    private UUID vehicleId;
}
