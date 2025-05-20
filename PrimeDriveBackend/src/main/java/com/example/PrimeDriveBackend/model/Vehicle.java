package com.example.PrimeDriveBackend.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    private String id;
    private String name;
    private Double price;
    private Date year;
    private String image;
    private Number mileage;
    private String condition;
    private String vehicleHistory;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_brands_id", referencedColumnName = "id")
    private VehicleBrands brands;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_specs_id", referencedColumnName = "id")
    private VehicleSpecs specs;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_types_id", referencedColumnName = "id")
    private VehicleTypes types;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_colors_id", referencedColumnName = "id")
    private VehicleColors colors;

    @ManyToOne
    @JoinColumn(name = "foreign_key_vehicle_seller_id", referencedColumnName = "id")
    private Users users;
}
