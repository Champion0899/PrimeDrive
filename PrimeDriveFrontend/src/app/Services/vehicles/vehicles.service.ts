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

/**
 * Service for accessing vehicle-related data from the backend API.
 * Provides methods for retrieving vehicles, brands, types, specs, colors, and associated metadata.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
@Injectable({
  providedIn: 'root',
})
export class VehiclesService {
  private apiUrl = 'https://localhost:8443/api';
  private httpClient = inject(HttpClient);
  /**
   * Retrieves a list of all vehicles from the backend.
   * @returns Observable of Vehicle array.
   */
  public getVehicles(): Observable<Vehicle[]> {
    return this.httpClient.get<Vehicle[]>(`${this.apiUrl}/vehicle`, {
      withCredentials: true,
    });
  }

  /**
   * Retrieves a vehicle by its unique identifier.
   * @param id - The UUID of the vehicle.
   * @returns Observable of the Vehicle.
   */
  public getVehicleById(id: string): Observable<Vehicle> {
    return this.httpClient.get<Vehicle>(`${this.apiUrl}/vehicle/${id}`, {
      withCredentials: true,
    });
  }

  /**
   * Retrieves the brand details for a given brand ID.
   * @param id - The UUID of the brand.
   * @returns Observable of the Brand.
   */
  public getBrandById(id: string): Observable<Brand> {
    return this.httpClient.get<Brand>(`${this.apiUrl}/vehicle_brands/${id}`, {
      withCredentials: true,
    });
  }

  /**
   * Retrieves a list of all vehicle holdings.
   * @returns Observable of Holding array.
   */
  public getHoldingById(id: string): Observable<Brand[]> {
    return this.httpClient.get<Brand[]>(
      `${this.apiUrl}/vehicle_holdings/${id}`,
      {
        withCredentials: true,
      }
    );
  }

  /**
   * Retrieves the vehicle type details by type ID.
   * @param id - The UUID of the vehicle type.
   * @returns Observable of the VehicleType.
   */
  public getTypeById(id: string): Observable<VehicleType> {
    return this.httpClient.get<VehicleType>(
      `${this.apiUrl}/vehicle_types/${id}`,
      {
        withCredentials: true,
      }
    );
  }

  /**
   * Retrieves the vehicle color details by color ID.
   * @param id - The UUID of the vehicle color.
   * @returns Observable of the Color.
   */
  public getColorById(id: string): Observable<Color> {
    return this.httpClient.get<Color>(`${this.apiUrl}/vehicle_colors/${id}`, {
      withCredentials: true,
    });
  }
  /**
   * Retrieves the technical specifications for a vehicle.
   * @param id - The UUID of the vehicle specs.
   * @returns Observable of the Specs.
   */
  public getSpecsById(id: string): Observable<Specs> {
    return this.httpClient.get<Specs>(`${this.apiUrl}/vehicle_specs/${id}`, {
      withCredentials: true,
    });
  }
  /**
   * Retrieves the engine configuration for a vehicle.
   * @param id - The UUID of the engine.
   * @returns Observable of the Engine.
   */
  public getEngineById(id: string): Observable<Engine> {
    return this.httpClient.get<Engine>(`${this.apiUrl}/vehicle_engine/${id}`, {
      withCredentials: true,
    });
  }
  /**
   * Retrieves the fuel type configuration for a vehicle.
   * @param id - The UUID of the fuel.
   * @returns Observable of the Fuel.
   */
  public getFuelById(id: string): Observable<Fuel> {
    return this.httpClient.get<Fuel>(`${this.apiUrl}/vehicle_fuels/${id}`, {
      withCredentials: true,
    });
  }
  /**
   * Retrieves the door configuration for a vehicle.
   * @param id - The UUID of the doors.
   * @returns Observable of the Doors.
   */
  public getDoorsById(id: string): Observable<Doors> {
    return this.httpClient.get<Doors>(`${this.apiUrl}/vehicle_doors/${id}`, {
      withCredentials: true,
    });
  }
  /**
   * Retrieves the seating configuration for a vehicle.
   * @param id - The UUID of the seats.
   * @returns Observable of the Seats.
   */
  public getSeatsById(id: string): Observable<Seats> {
    return this.httpClient.get<Seats>(`${this.apiUrl}/vehicle_seats/${id}`, {
      withCredentials: true,
    });
  }
  /**
   * Retrieves user data by user ID.
   * @param id - The UUID of the user.
   * @returns Observable of the User.
   */
  public getUserById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/users/${id}`, {
      withCredentials: true,
    });
  }
}
