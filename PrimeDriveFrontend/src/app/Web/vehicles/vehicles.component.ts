import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Vehicle } from '../../Models/vehicles/vehicle.interface';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { forkJoin, map, switchMap } from 'rxjs';
import { VehicleWithLessDetails } from '../../Models/vehicles/vehicleWithLessDetails';
import { VehiclesService } from '../../Services/vehicles/vehicles.service';

@Component({
  selector: 'app-vehicles',
  standalone: true,
  imports: [MatCardModule, CommonModule],
  templateUrl: './vehicles.component.html',
  styleUrl: './vehicles.component.scss',
})
export class VehiclesComponent implements OnInit {
  public vehicles: VehicleWithLessDetails[] = [];
  private vehiclesService = inject(VehiclesService);
  private router = inject(Router);

  public ngOnInit(): void {
    this.getVehicles();
  }

  public goToVehicleDetails(vehicleId: string): void {
    this.router.navigate(['/vehicles', vehicleId]);
  }

  private getVehicles() {
    this.vehiclesService
      .getVehicles()
      .pipe(
        switchMap((vehicles: Vehicle[]) => {
          const detailedVehicles$ = vehicles.map((vehicle: Vehicle) =>
            forkJoin({
              brand: this.vehiclesService.getBrandById(vehicle.brandsId),
              type: this.vehiclesService.getTypeById(vehicle.typesId),
            }).pipe(
              map(
                (details) =>
                  ({
                    ...vehicle,
                    brand: details.brand,
                    type: details.type,
                  } as unknown as VehicleWithLessDetails)
              )
            )
          );
          return forkJoin(detailedVehicles$);
        })
      )
      .subscribe({
        next: (vehicles: VehicleWithLessDetails[]) => {
          this.vehicles = vehicles;
        },
        error: (error: HttpErrorResponse) => {
          console.error('Error fetching vehicles:', error.message);
        },
      });
  }
}
