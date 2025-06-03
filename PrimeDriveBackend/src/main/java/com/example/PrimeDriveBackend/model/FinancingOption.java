package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Entity representing a financing option for a specific vehicle.
 *
 * Stores financial terms such as interest rate, maturity period, monthly rate,
 * and type of financing.
 * Each financing option is associated with exactly one vehicle.
 *
 * Author: Jamie Schüpbach & Lorin Baumann
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancingOption {

    /** Unique identifier for the financing option. */
    @Id
    private UUID id;

    /** Type of financing (e.g., leasing, credit). */
    private String art;

    /** Interest rate applied to the financing. */
    private Double interestRate;

    /** Duration of the financing in months. */
    private Integer maturity;

    /** Monthly payment amount under this financing option. */
    private Double monthlyRate;

    /** The vehicle associated with this financing option. */
    @OneToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
}
