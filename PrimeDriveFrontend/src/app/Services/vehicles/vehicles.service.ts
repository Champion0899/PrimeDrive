import { HttpClient } from '@angular/common/http';
import { Injectable, Type, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehicle } from '../../Models/vehicles/vehicle.interface';
import { Brand } from '../../Models/vehicles/brand.interface';
import { Type as VehicleType } from '../../Models/vehicles/type.interface';
import { Color } from '../../Models/vehicles/color.interface';
import { Specs } from '../../Models/vehicles/specs.interface';
import { Engine } from '../../Models/vehicles/engine.interface';
import { Fuel } from '../../Models/vehicles/fuel.interface';
import { Doors } from '../../Models/vehicles/doors.interface';
import { Seats } from '../../Models/vehicles/seats.interface';
import { User } from '../../Models/vehicles/user.interface';

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

  public getVehicleById(id: string): Observable<Vehicle> {
    return this.httpClient.get<Vehicle>(`${this.apiUrl}/vehicle/${id}`, {
      withCredentials: true,
    });
  }

  public getBrandById(id: string): Observable<Brand> {
    return this.httpClient.get<Brand>(`${this.apiUrl}/vehicle_brands/${id}`, {
      withCredentials: true,
    });
  }

  public getTypeById(id: string): Observable<VehicleType> {
    return this.httpClient.get<VehicleType>(
      `${this.apiUrl}/vehicle_types/${id}`,
      {
        withCredentials: true,
      }
    );
  }

  public getColorById(id: string): Observable<Color> {
    return this.httpClient.get<Color>(`${this.apiUrl}/vehicle_colors/${id}`, {
      withCredentials: true,
    });
  }
  public getSpecsById(id: string): Observable<Specs> {
    return this.httpClient.get<Specs>(`${this.apiUrl}/vehicle_specs/${id}`, {
      withCredentials: true,
    });
  }
  public getEngineById(id: string): Observable<Engine> {
    return this.httpClient.get<Engine>(`${this.apiUrl}/vehicle_engine/${id}`, {
      withCredentials: true,
    });
  }
  public getFuelById(id: string): Observable<Fuel> {
    return this.httpClient.get<Fuel>(`${this.apiUrl}/vehicle_fuels/${id}`, {
      withCredentials: true,
    });
  }
  public getDoorsById(id: string): Observable<Doors> {
    return this.httpClient.get<Doors>(`${this.apiUrl}/vehicle_doors/${id}`, {
      withCredentials: true,
    });
  }
  public getSeatsById(id: string): Observable<Seats> {
    return this.httpClient.get<Seats>(`${this.apiUrl}/vehicle_seats/${id}`, {
      withCredentials: true,
    });
  }
  public getUserById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/users/${id}`, {
      withCredentials: true,
    });
  }
}
