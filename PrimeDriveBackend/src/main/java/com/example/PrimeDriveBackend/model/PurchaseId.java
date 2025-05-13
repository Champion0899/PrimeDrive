package com.example.PrimeDriveBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseId implements Serializable {
    private UUID userId;
    private UUID vehicleId;
}
