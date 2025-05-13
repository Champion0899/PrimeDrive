package com.example.PrimeDriveBackend.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHoldings {
    @Id
    private String id;
    private String name;
    private Date founding;
    private String logo;
}
