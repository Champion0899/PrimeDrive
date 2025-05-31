import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiclesService } from '../../vehicles/vehicles.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Vehicle } from '../../Models/vehicles/vehicle.interface';
import { MatCardModule } from '@angular/material/card';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-vehicles',
  standalone: true,
  imports: [MatCardModule, CommonModule],
  templateUrl: './vehicles.component.html',
  styleUrl: './vehicles.component.scss',
})
export class VehiclesComponent implements OnInit {
  public vehicles: Vehicle[] = [];

  private vehiclesService = inject(VehiclesService);
  ngOnInit(): void {
    this.getVehicles();
  }

  public getVehicles() {
    this.vehiclesService.getVehicles().subscribe({
      next: (vehicles) => {
        this.vehicles = vehicles;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching vehicles:', error);
      },
    });
  }
}
