package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancingOption {

    @Id
    private UUID id;

    private String art;

    private Double interestRate;

    private Integer maturity;

    private Double monthlyRate;

    @OneToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
}
