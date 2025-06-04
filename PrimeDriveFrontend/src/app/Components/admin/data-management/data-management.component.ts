import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { Color } from '../../../Models/vehicles/color.interface';
import { Brand } from '../../../Models/vehicles/brand.interface';
import { Doors } from '../../../Models/vehicles/doors.interface';
import { Engine } from '../../../Models/vehicles/engine.interface';
import { Fuel } from '../../../Models/vehicles/fuel.interface';
import { Holding } from '../../../Models/vehicles/holding.interface';
import { Seats } from '../../../Models/vehicles/seats.interface';
import { Type } from '../../../Models/vehicles/type.interface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-data-management',
  standalone: true,
  imports: [
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    MatButtonModule
  ],
  templateUrl: './data-management.component.html',
  styleUrl: './data-management.component.scss'
})
export class DataManagementComponent {
  public dataSource = [];

  public colors: Color[] = [];
  public selectedColor: Color | null = null;
  public searchedColorId: string = '';
  fetchColorById(): void {
    const found = this.colors.find(c => c.id === this.searchedColorId);
    this.selectedColor = found ? { ...found } : null;
  }
  updateColor(): void {
    if (!this.selectedColor) return;
    const index = this.colors.findIndex(c => c.id === this.selectedColor!.id);
    if (index !== -1) this.colors[index] = { ...this.selectedColor };
    this.selectedColor = null;
  }
  deleteColor(): void {
    if (!this.selectedColor) return;
    this.colors = this.colors.filter(c => c.id !== this.selectedColor!.id);
    this.selectedColor = null;
  }

  public doors: Doors[] = [];
  public selectedDoors: Doors | null = null;
  public searchedDoorsId: string = '';
  fetchDoorsById(): void {
    const found = this.doors.find(d => d.id === this.searchedDoorsId);
    this.selectedDoors = found ? { ...found } : null;
  }
  updateDoors(): void {
    if (!this.selectedDoors) return;
    const index = this.doors.findIndex(d => d.id === this.selectedDoors!.id);
    if (index !== -1) this.doors[index] = { ...this.selectedDoors };
    this.selectedDoors = null;
  }
  deleteDoors(): void {
    if (!this.selectedDoors) return;
    this.doors = this.doors.filter(d => d.id !== this.selectedDoors!.id);
    this.selectedDoors = null;
  }

  public engines: Engine[] = [];
  public selectedEngine: Engine | null = null;
  public searchedEngineId: string = '';
  fetchEngineById(): void {
    const found = this.engines.find(e => e.id === this.searchedEngineId);
    this.selectedEngine = found ? { ...found } : null;
  }
  updateEngine(): void {
    if (!this.selectedEngine) return;
    const index = this.engines.findIndex(e => e.id === this.selectedEngine!.id);
    if (index !== -1) this.engines[index] = { ...this.selectedEngine };
    this.selectedEngine = null;
  }
  deleteEngine(): void {
    if (!this.selectedEngine) return;
    this.engines = this.engines.filter(e => e.id !== this.selectedEngine!.id);
    this.selectedEngine = null;
  }

  public fuels: Fuel[] = [];
  public selectedFuel: Fuel | null = null;
  public searchedFuelId: string = '';
  fetchFuelById(): void {
    const found = this.fuels.find(f => f.id === this.searchedFuelId);
    this.selectedFuel = found ? { ...found } : null;
  }
  updateFuel(): void {
    if (!this.selectedFuel) return;
    const index = this.fuels.findIndex(f => f.id === this.selectedFuel!.id);
    if (index !== -1) this.fuels[index] = { ...this.selectedFuel };
    this.selectedFuel = null;
  }
  deleteFuel(): void {
    if (!this.selectedFuel) return;
    this.fuels = this.fuels.filter(f => f.id !== this.selectedFuel!.id);
    this.selectedFuel = null;
  }

  public holdings: Holding[] = [];
  public selectedHolding: Holding | null = null;
  public searchedHoldingId: string = '';
  fetchHoldingById(): void {
    const found = this.holdings.find(h => h.id === this.searchedHoldingId);
    this.selectedHolding = found ? { ...found } : null;
  }
  updateHolding(): void {
    if (!this.selectedHolding) return;
    const index = this.holdings.findIndex(h => h.id === this.selectedHolding!.id);
    if (index !== -1) this.holdings[index] = { ...this.selectedHolding };
    this.selectedHolding = null;
  }
  deleteHolding(): void {
    if (!this.selectedHolding) return;
    this.holdings = this.holdings.filter(h => h.id !== this.selectedHolding!.id);
    this.selectedHolding = null;
  }

  public seats: Seats[] = [];
  public selectedSeats: Seats | null = null;
  public searchedSeatsId: string = '';
  fetchSeatsById(): void {
    const found = this.seats.find(s => s.id === this.searchedSeatsId);
    this.selectedSeats = found ? { ...found } : null;
  }
  updateSeats(): void {
    if (!this.selectedSeats) return;
    const index = this.seats.findIndex(s => s.id === this.selectedSeats!.id);
    if (index !== -1) this.seats[index] = { ...this.selectedSeats };
    this.selectedSeats = null;
  }
  deleteSeats(): void {
    if (!this.selectedSeats) return;
    this.seats = this.seats.filter(s => s.id !== this.selectedSeats!.id);
    this.selectedSeats = null;
  }

  public types: Type[] = [];
  public selectedType: Type | null = null;
  public searchedTypeId: string = '';
  fetchTypeById(): void {
    const found = this.types.find(t => t.id === this.searchedTypeId);
    this.selectedType = found ? { ...found } : null;
  }
  updateType(): void {
    if (!this.selectedType) return;
    const index = this.types.findIndex(t => t.id === this.selectedType!.id);
    if (index !== -1) this.types[index] = { ...this.selectedType };
    this.selectedType = null;
  }
  deleteType(): void {
    if (!this.selectedType) return;
    this.types = this.types.filter(t => t.id !== this.selectedType!.id);
    this.selectedType = null;
  }

  public brands: Brand[] = [];
  public selectedBrand: Brand | null = null;
  public searchedBrandId: string = '';
  fetchBrandById(): void {
    const found = this.brands.find(b => b.id === this.searchedBrandId);
    this.selectedBrand = found ? { ...found } : null;
  }
  updateBrand(): void {
    if (!this.selectedBrand) return;
    const index = this.brands.findIndex(b => b.id === this.selectedBrand!.id);
    if (index !== -1) this.brands[index] = { ...this.selectedBrand };
    this.selectedBrand = null;
  }
  deleteBrand(): void {
    if (!this.selectedBrand) return;
    this.brands = this.brands.filter(b => b.id !== this.selectedBrand!.id);
    this.selectedBrand = null;
  }
}
