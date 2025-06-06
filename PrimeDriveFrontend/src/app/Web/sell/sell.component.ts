import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
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
import { Type as VehicleType } from '../../Models/vehicles/type.interface';
import { Specs } from '../../Models/vehicles/specs.interface';
import { VehiclesService } from '../../Services/vehicles/vehicles.service';

@Component({
  selector: 'app-sell',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
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
  private vehicleService = inject(VehiclesService);

  form!: FormGroup;
  specsForm!: FormGroup;

  brands: Brand[] = [];
  colors: Color[] = [];
  types: VehicleType[] = [];
  specs: Specs[] = [];
  doors: any[] = [];
  seats: any[] = [];
  engines: any[] = [];
  fuels: any[] = [];

  userVehicles: Vehicle[] = [];

  constructor(private fb: FormBuilder) {}

  onSubmit() {
    console.log('Vehicle submitted:', this.form.value);
    console.log('Specs submitted:', this.specsForm.value);

    this.form.reset({
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
    });

    this.specsForm.reset({
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
    });
  }

  editVehicle(vehicle: Vehicle) {
    this.form.patchValue(vehicle);
  }

  deleteVehicle(id: string) {
    this.userVehicles = this.userVehicles.filter((v) => v.id !== id);
  }

  ngOnInit() {
    this.vehicleService.getBrands().subscribe((data) => (this.brands = data));
    this.vehicleService.getColors().subscribe((data) => (this.colors = data));
    this.vehicleService.getTypes().subscribe((data) => (this.types = data));
    this.vehicleService.getDoors().subscribe((data) => {
      this.doors = data;
      console.log('Doors:', data);
    });
    this.vehicleService.getSeats().subscribe((data) => {
      this.seats = data;
      console.log('Seats:', data);
    });
    this.vehicleService.getEngines().subscribe((data) => {
      this.engines = data;
      console.log('Engines:', data);
    });
    this.vehicleService.getFuels().subscribe((data) => {
      this.fuels = data;
      console.log('Fuels:', data);
    });

    this.form = this.fb.group({
      id: [''],
      name: [''],
      price: [0],
      year: [new Date().getFullYear()],
      image: [''],
      mileage: [0],
      condition: [''],
      vehicleHistory: [''],
      brandsId: [''],
      specsId: [''],
      typesId: [''],
      colorsId: [''],
      sellerId: [''],
    });

    this.specsForm = this.fb.group({
      id: [''],
      powerKw: [0],
      powerPs: [0],
      lengthMillimeters: [0],
      widthMillimeters: [0],
      heightMillimeters: [0],
      trunkInLiterMin: [0],
      trunkInLiterMax: [0],
      zeroToHundredInSeconds: [0],
      topSpeedInKmH: [0],
      consumptionHundredInX: [0],
      coTwoEmissionsInGPerKm: [0],
      cubicCapacity: [0],
      doorsId: [''],
      seatsId: [''],
      engineId: [''],
      fuelsId: [''],
    });
  }
}
