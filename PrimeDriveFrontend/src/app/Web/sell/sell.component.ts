import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatDividerModule } from '@angular/material/divider';
import { Vehicle } from '../../Models/vehicles/vehicle.interface';
import { Brand } from '../../Models/vehicles/brand.interface';
import { Color } from '../../Models/vehicles/color.interface';
import { Type as VehicleType } from '../../Models/vehicles/type.interface'
import { Specs } from '../../Models/vehicles/specs.interface';

@Component({
  selector: 'app-sell',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatTableModule,
    MatDividerModule,
  ],
  templateUrl: './sell.component.html',
  styleUrl: './sell.component.scss',
})
export class SellComponent {
  vehicle: Vehicle = {
    id: '',
    name: '',
    price: 0,
    year: new Date().getFullYear(),
    image: '',
    mileage: 0,
    condition: '',
    vehicleHistory: '',
    brandsId: '',
    specsId: '',
    typesId: '',
    colorsId: '',
    sellerId: '',
  };

  brands: Brand[] = [];
  colors: Color[] = [];
  types: VehicleType[] = [];
  specs: Specs[] = [];
  doors: any[] = [];
  seats: any[] = [];
  engines: any[] = [];
  fuels: any[] = [];
  spec: Specs = {
    id: '',
    powerKw: 0,
    powerPs: 0,
    lengthMillimeters: 0,
    widthMillimeters: 0,
    heightMillimeters: 0,
    trunkInLiterMin: 0,
    trunkInLiterMax: 0,
    zeroToHundredInSeconds: 0,
    topSpeedInKmH: 0,
    consumptionHundredInX: 0,
    coTwoEmissionsInGPerKm: 0,
    cubicCapacity: 0,
    doorsId: '',
    seatsId: '',
    engineId: '',
    fuelsId: '',
  };

  userVehicles: Vehicle[] = [];

  onSubmit() {
    // Combine vehicle and spec before processing
    console.log('Vehicle submitted:', this.vehicle);
    console.log('Specs submitted:', this.spec);

    // Hier könntest du die spec speichern oder mit vehicle verknüpfen
    // this.vehicle.specsId = this.spec.id; // falls Specs separat gespeichert werden
    // this.userVehicles.push({...this.vehicle}); // falls lokal gespeichert wird

    // Reset after submission
    this.vehicle = {
      id: '',
      name: '',
      price: 0,
      year: new Date().getFullYear(),
      image: '',
      mileage: 0,
      condition: '',
      vehicleHistory: '',
      brandsId: '',
      specsId: '',
      typesId: '',
      colorsId: '',
      sellerId: '',
    };

    this.spec = {
      id: '',
      powerKw: 0,
      powerPs: 0,
      lengthMillimeters: 0,
      widthMillimeters: 0,
      heightMillimeters: 0,
      trunkInLiterMin: 0,
      trunkInLiterMax: 0,
      zeroToHundredInSeconds: 0,
      topSpeedInKmH: 0,
      consumptionHundredInX: 0,
      coTwoEmissionsInGPerKm: 0,
      cubicCapacity: 0,
      doorsId: '',
      seatsId: '',
      engineId: '',
      fuelsId: '',
    };
  }

  editVehicle(vehicle: Vehicle) {
    this.vehicle = { ...vehicle };
  }

  deleteVehicle(id: string) {
    this.userVehicles = this.userVehicles.filter((v) => v.id !== id);
  }
}
