package com.example.PrimeDriveBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Entity representing a scheduled test drive for a specific vehicle by a user.
 *
 * Contains date, time, location, and status information, and is linked to a
 * user and a vehicle.
 * Each vehicle can only have one active test drive due to the unique
 * constraint.
 *
 * Author: Jamie Schüpbach & Lorin Baumann
 * Version: 1.0
 * Date: 2025-06-03
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDrive {

    /** Unique identifier for the test drive. */
    @Id
    private UUID testDriveId;

    /** Date of the scheduled test drive. */
    private LocalDate date;

    /** Time of the scheduled test drive. */
    private LocalTime time;

    /** City where the test drive will take place. */
    private String city;

    /** Current status of the test drive (e.g., scheduled, completed, cancelled). */
    private String status;

    /** The user who booked the test drive. */
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    /** The vehicle that is to be test-driven. */
    @OneToOne
    @JoinColumn(name = "vehicleId", unique = true)
    private Vehicle vehicle;
}
