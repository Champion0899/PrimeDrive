import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehicle } from '../Models/vehicles/vehicle.interface';

@Injectable({
  providedIn: 'root',
})
export class VehiclesService {
  private apiUrl = 'https://localhost:8443/api';
  private httpClient = inject(HttpClient);
  public getVehicles(): Observable<Vehicle[]> {
    return this.httpClient.get<Vehicle[]>(`${this.apiUrl}/vehicle`, {
      withCredentials: true,
    });
  }
}
