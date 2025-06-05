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
import { VehiclesService } from '../../../Services/vehicles/vehicles.service';
import { MatOption, MatOptionModule } from '@angular/material/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-data-management',
  standalone: true,
  imports: [
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    MatButtonModule,
    CommonModule,
    MatOptionModule,
  ],
  providers: [VehiclesService],
  templateUrl: './data-management.component.html',
  styleUrl: './data-management.component.scss',
})
export class DataManagementComponent {
  public dataSource = [];

  public colors: Color[] = [];
  public selectedColor: Color | null = null;
  public searchedColorName: string = '';
  public newHolding: Holding = {
    id: '',
    founding: 0,
    logo: '',
    name: '',
  };

  public newBrand: Brand = {
    id: '',
    holdingId: '',
    founding: 0,
    logo: '',
    name: '',
  };

  public newType: Type = { id: '', type: '' };
  public newEngine: Engine = { id: '', engineType: '' };
  public newFuel: Fuel = { id: '', fuelType: '' };
  public newDoors: Doors = { id: '', quantity: 0 };
  public newSeats: Seats = { id: '', quantity: 0 };
  public newColor: Color = { id: '', name: '', hexCode: '' };

  public brands: Brand[] = [];
  public selectedBrand: Brand | null = null;
  public searchedBrandName: string = '';

  public doors: Doors[] = [];
  public selectedDoors: Doors | null = null;
  public searchedDoorsName: string = '';

  public engines: Engine[] = [];
  public selectedEngine: Engine | null = null;
  public searchedEngineName: string = '';

  public fuels: Fuel[] = [];
  public selectedFuel: Fuel | null = null;
  public searchedFuelName: string = '';

  public holdings: Holding[] = [];
  public selectedHolding: Holding | null = null;
  public searchedHoldingName: string = '';

  public seats: Seats[] = [];
  public selectedSeats: Seats | null = null;
  public searchedSeatsName: string = '';

  public types: Type[] = [];
  public selectedType: Type | null = null;
  public searchedTypeName: string = '';

  constructor(private vehiclesService: VehiclesService) {
    this.loadAllColors();
    this.loadAllDoors();
    this.loadAllEngines();
    this.loadAllFuels();
    this.loadAllHoldings();
    this.loadAllSeats();
    this.loadAllTypes();
    this.loadAllBrands();
  }

  fetchColorByName(): void {
    this.selectedColor =
      this.colors.find((color) =>
        color.name.toLowerCase().includes(this.searchedColorName.toLowerCase())
      ) ?? null;
  }
  updateColor(): void {
    if (!this.selectedColor) return;
    this.vehiclesService.updateColor(this.selectedColor).subscribe(() => {
      this.selectedColor = null;
      this.loadAllColors();
    });
  }
  selectColor(item: Color): void {
    this.selectedColor = { ...item };
  }
  deleteColor(id?: string): void {
    const colorId = id ?? this.selectedColor?.id;
    if (!colorId) return;
    this.vehiclesService.deleteColor(colorId).subscribe(() => {
      this.selectedColor = null;
      this.loadAllColors();
    });
  }
  createColor(): void {
    const name = this.newColor.name.trim();
    const hex = this.newColor.hexCode.trim();
    if (!name || !hex) return;
    this.vehiclesService
      .createColor({ id: '', name, hexCode: hex })
      .subscribe(() => {
        this.loadAllColors();
        this.newColor = { id: '', name: '', hexCode: '' };
      });
  }
  loadAllColors(): void {
    this.vehiclesService.getColors().subscribe((data: Color[]) => {
      this.colors = data;
    });
  }

  fetchDoorsByName(): void {
    this.selectedDoors =
      this.doors.find((doors) =>
        doors.quantity
          .toString()
          .toLowerCase()
          .includes(this.searchedDoorsName.toLowerCase())
      ) ?? null;
  }
  updateDoors(): void {
    if (!this.selectedDoors) return;
    this.vehiclesService.updateDoors(this.selectedDoors).subscribe(() => {
      this.selectedDoors = null;
      this.loadAllDoors();
    });
  }
  selectDoors(item: Doors): void {
    this.selectedDoors = { ...item };
  }
  deleteDoors(id?: string): void {
    const doorsId = id ?? this.selectedDoors?.id;
    if (!doorsId) return;
    this.vehiclesService.deleteDoors(doorsId).subscribe(() => {
      this.selectedDoors = null;
      this.loadAllDoors();
    });
  }
  loadAllDoors(): void {
    this.vehiclesService.getDoors().subscribe((data: Doors[]) => {
      this.doors = data;
    });
  }
  createDoors(): void {
    if (this.newDoors.quantity <= 0) return;
    this.vehiclesService.createDoors(this.newDoors).subscribe(() => {
      this.loadAllDoors();
      this.newDoors = { id: '', quantity: 0 };
    });
  }

  fetchEngineByName(): void {
    this.selectedEngine =
      this.engines.find((engine) =>
        engine.engineType
          .toLowerCase()
          .includes(this.searchedEngineName.toLowerCase())
      ) ?? null;
  }
  updateEngine(): void {
    if (!this.selectedEngine) return;
    this.vehiclesService.updateEngine(this.selectedEngine).subscribe(() => {
      this.selectedEngine = null;
      this.loadAllEngines();
    });
  }
  selectEngine(item: Engine): void {
    this.selectedEngine = { ...item };
  }
  deleteEngine(id?: string): void {
    const engineId = id ?? this.selectedEngine?.id;
    if (!engineId) return;
    this.vehiclesService.deleteEngine(engineId).subscribe(() => {
      this.selectedEngine = null;
      this.loadAllEngines();
    });
  }
  loadAllEngines(): void {
    this.vehiclesService.getEngines().subscribe((data: Engine[]) => {
      this.engines = data;
    });
  }
  createEngine(): void {
    const engineValue = this.newEngine.engineType.trim();
    if (!engineValue) return;
    this.vehiclesService
      .createEngine({ id: '', engineType: engineValue })
      .subscribe(() => {
        this.loadAllEngines();
        this.newEngine = { id: '', engineType: '' };
      });
  }

  fetchFuelByName(): void {
    this.selectedFuel =
      this.fuels.find((fuel) =>
        fuel.fuelType
          .toLowerCase()
          .includes(this.searchedFuelName.toLowerCase())
      ) ?? null;
  }
  updateFuel(): void {
    if (!this.selectedFuel) return;
    this.vehiclesService.updateFuel(this.selectedFuel).subscribe(() => {
      this.selectedFuel = null;
      this.loadAllFuels();
    });
  }
  selectFuel(item: Fuel): void {
    this.selectedFuel = { ...item };
  }
  deleteFuel(id?: string): void {
    const fuelId = id ?? this.selectedFuel?.id;
    if (!fuelId) return;
    this.vehiclesService.deleteFuel(fuelId).subscribe(() => {
      this.selectedFuel = null;
      this.loadAllFuels();
    });
  }
  loadAllFuels(): void {
    this.vehiclesService.getFuels().subscribe((data: Fuel[]) => {
      this.fuels = data;
    });
  }
  createFuel(): void {
    const fuelValue = this.newFuel.fuelType.trim();
    if (!fuelValue) return;
    this.vehiclesService
      .createFuel({ id: '', fuelType: fuelValue })
      .subscribe(() => {
        this.loadAllFuels();
        this.newFuel = { id: '', fuelType: '' };
      });
  }

  fetchHoldingByName(): void {
    this.selectedHolding =
      this.holdings.find((holding) =>
        holding.name
          .toLowerCase()
          .includes(this.searchedHoldingName.toLowerCase())
      ) ?? null;
  }
  updateHolding(): void {
    if (!this.selectedHolding) return;
    this.vehiclesService.updateHolding(this.selectedHolding).subscribe(() => {
      this.selectedHolding = null;
      this.loadAllHoldings();
    });
  }
  selectHolding(item: Holding): void {
    this.selectedHolding = { ...item };
  }
  deleteHolding(id?: string): void {
    const holdingId = id ?? this.selectedHolding?.id;
    if (!holdingId) return;
    this.vehiclesService.deleteHolding(holdingId).subscribe(() => {
      this.selectedHolding = null;
      this.loadAllHoldings();
    });
  }
  loadAllHoldings(): void {
    this.vehiclesService.getHoldings().subscribe((data: Holding[]) => {
      this.holdings = data;
    });
  }

  createHolding(): void {
    this.vehiclesService.createHolding(this.newHolding).subscribe(() => {
      this.loadAllHoldings();
      this.newHolding = { id: '', founding: 0, logo: '', name: '' };
    });
  }

  fetchSeatsByName(): void {
    this.selectedSeats =
      this.seats.find((seats) =>
        seats.quantity
          .toString()
          .toLowerCase()
          .includes(this.searchedSeatsName.toLowerCase())
      ) ?? null;
  }
  updateSeats(): void {
    if (!this.selectedSeats) return;
    this.vehiclesService.updateSeats(this.selectedSeats).subscribe(() => {
      this.selectedSeats = null;
      this.loadAllSeats();
    });
  }
  selectSeats(item: Seats): void {
    this.selectedSeats = { ...item };
  }
  deleteSeats(id?: string): void {
    const seatsId = id ?? this.selectedSeats?.id;
    if (!seatsId) return;
    this.vehiclesService.deleteSeats(seatsId).subscribe(() => {
      this.selectedSeats = null;
      this.loadAllSeats();
    });
  }
  loadAllSeats(): void {
    this.vehiclesService.getSeats().subscribe((data: Seats[]) => {
      this.seats = data;
    });
  }
  createSeats(): void {
    if (this.newSeats.quantity <= 0) return;
    this.vehiclesService.createSeats(this.newSeats).subscribe(() => {
      this.loadAllSeats();
      this.newSeats = { id: '', quantity: 0 };
    });
  }

  fetchTypeByName(): void {
    this.selectedType =
      this.types.find((type) =>
        type.type.toLowerCase().includes(this.searchedTypeName.toLowerCase())
      ) ?? null;
  }
  updateType(): void {
    if (!this.selectedType) return;
    this.vehiclesService.updateType(this.selectedType).subscribe(() => {
      this.selectedType = null;
      this.loadAllTypes();
    });
  }
  createType(): void {
    const typeValue = this.newType.type.trim();
    if (!typeValue) return;
    this.vehiclesService
      .createType({ id: '', type: typeValue })
      .subscribe(() => {
        this.loadAllTypes();
        this.newType = { id: '', type: '' };
      });
  }
  selectType(item: Type): void {
    this.selectedType = { ...item };
  }
  deleteType(id?: string): void {
    const typeId = id ?? this.selectedType?.id;
    if (!typeId) return;
    this.vehiclesService.deleteType(typeId).subscribe(() => {
      this.selectedType = null;
      this.loadAllTypes();
    });
  }
  loadAllTypes(): void {
    this.vehiclesService.getTypes().subscribe((data: Type[]) => {
      this.types = data;
    });
  }

  fetchBrandByName(): void {
    this.selectedBrand =
      this.brands.find((brand) =>
        brand.name.toLowerCase().includes(this.searchedBrandName.toLowerCase())
      ) ?? null;
  }
  updateBrand(): void {
    if (!this.selectedBrand) return;
    this.vehiclesService.updateBrand(this.selectedBrand).subscribe(() => {
      this.selectedBrand = null;
      this.loadAllBrands();
    });
  }
  selectBrand(item: Brand): void {
    this.selectedBrand = { ...item };
  }
  deleteBrand(id?: string): void {
    const brandId = id ?? this.selectedBrand?.id;
    if (!brandId) return;
    this.vehiclesService.deleteBrand(brandId).subscribe(() => {
      this.selectedBrand = null;
      this.loadAllBrands();
    });
  }
  loadAllBrands(): void {
    this.vehiclesService.getBrands().subscribe((data: Brand[]) => {
      this.brands = data;
    });
  }

  createBrand(): void {
    this.vehiclesService.createBrand(this.newBrand).subscribe(() => {
      this.loadAllBrands();
      this.newBrand = {
        id: '',
        holdingId: '',
        founding: 0,
        logo: '',
        name: '',
      };
    });
  }
}
