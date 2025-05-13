package com.example.PrimeDriveBackend.model;

import java.util.UUID;

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
    private UUID id;

    private String image;

    private String brand;

    private String model;

    private Integer yearBuild;

    private Integer mileage;

    private Double price;

    private String condition;

    @Lob
    private String vehicleHistory;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Users user;
}
