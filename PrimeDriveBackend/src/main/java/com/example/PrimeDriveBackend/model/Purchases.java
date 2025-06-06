package com.example.PrimeDriveBackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a purchase transaction.
 *
 * Each purchase has a unique UUID string as its primary key.
 * This entity establishes many-to-one relationships to the seller and buyer
 * (Users),
 * as well as to the vehicle involved in the purchase.
 *
 * The seller and buyer are linked by foreign keys 'sellerId' and 'buyerId'.
 * The purchased vehicle is linked by foreign key 'vehicleId'.
 *
 * Author: Fatlum Epiroti
 * Version: 1.2
 * Date: 2025-06-06
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchases {

    /** Unique UUID string serving as the primary key of the purchase. */
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /** Many-to-one relationship to the seller user, linked by 'sellerId'. */
    @ManyToOne
    @JoinColumn(name = "sellerId", insertable = false, updatable = false)
    private Users seller;

    /** Many-to-one relationship to the buyer user, linked by 'buyerId'. */
    @ManyToOne
    @JoinColumn(name = "buyerId", insertable = false, updatable = false)
    private Users buyer;

    /** Many-to-one relationship to the purchased vehicle, linked by 'vehicleId'. */
    @ManyToOne
    @JoinColumn(name = "vehicleId", insertable = false, updatable = false)
    private Vehicle vehicle;
}
