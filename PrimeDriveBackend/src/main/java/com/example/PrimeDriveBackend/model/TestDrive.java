package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDrive {

    @Id
    private UUID testDriveId;

    private LocalDate date;

    private LocalTime time;

    private String city;

    private String status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @OneToOne
    @JoinColumn(name = "vehicleId", unique = true)
    private Vehicle vehicle;
}
