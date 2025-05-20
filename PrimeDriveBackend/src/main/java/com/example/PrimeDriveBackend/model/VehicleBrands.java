package com.example.PrimeDriveBackend.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBrands {

    @Id
    private String id;
    private String name;
    private Date founding;
    private String logo;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_holding_id", referencedColumnName = "id")
    private VehicleHoldings holding;
}
