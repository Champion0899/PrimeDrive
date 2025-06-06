import { Component } from '@angular/core';
import { inject } from '@angular/core';
import { OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
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
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-data-management',
  standalone: true,
  imports: [
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    CommonModule,
    MatSelectModule,
  ],
  providers: [VehiclesService],
  templateUrl: './data-management.component.html',
  styleUrl: './data-management.component.scss',
})
export class DataManagementComponent implements OnInit {
  private vehiclesService = inject(VehiclesService);
  public errorMessageCreate: string | null = null;
  public errorMessageUpdate: string | null = null;
  public dataSource = [];

  public colors: Color[] = [];
  public selectedColor: Color | null = null;
  public searchedColorName: string = '';

  public brands: Brand[] = [];
  public selectedBrand: Brand | null = {
    id: '',
    holdingId: '',
    founding: 1970,
    logo: '',
    name: '',
  };
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
  public selectedHolding: Holding | null = {
    id: '',
    founding: 1970,
    logo: '',
    name: '',
  };
  public searchedHoldingName: string = '';

  public seats: Seats[] = [];
  public selectedSeats: Seats | null = null;
  public searchedSeatsName: string = '';

  public types: Type[] = [];
  public selectedType: Type | null = null;
  public searchedTypeName: string = '';

  public formGroup: FormGroup = new FormGroup({});
  public updateFormGroup: FormGroup = new FormGroup({});

  get colorForm(): FormGroup {
    return this.formGroup.get('color') as FormGroup;
  }
  get brandForm(): FormGroup {
    return this.formGroup.get('brand') as FormGroup;
  }
  get typeForm(): FormGroup {
    return this.formGroup.get('type') as FormGroup;
  }
  get engineForm(): FormGroup {
    return this.formGroup.get('engine') as FormGroup;
  }
  get fuelForm(): FormGroup {
    return this.formGroup.get('fuel') as FormGroup;
  }
  get doorsForm(): FormGroup {
    return this.formGroup.get('doors') as FormGroup;
  }
  get seatsForm(): FormGroup {
    return this.formGroup.get('seats') as FormGroup;
  }
  get holdingForm(): FormGroup {
    return this.formGroup.get('holding') as FormGroup;
  }

  get brandUpdateForm(): FormGroup {
    return this.updateFormGroup.get('brand') as FormGroup;
  }
  get colorUpdateForm(): FormGroup {
    return this.updateFormGroup.get('color') as FormGroup;
  }
  get holdingUpdateForm(): FormGroup {
    return this.updateFormGroup.get('holding') as FormGroup;
  }
  get typeUpdateForm(): FormGroup {
    return this.updateFormGroup.get('type') as FormGroup;
  }
  get engineUpdateForm(): FormGroup {
    return this.updateFormGroup.get('engine') as FormGroup;
  }
  get fuelUpdateForm(): FormGroup {
    return this.updateFormGroup.get('fuel') as FormGroup;
  }
  get doorsUpdateForm(): FormGroup {
    return this.updateFormGroup.get('doors') as FormGroup;
  }
  get seatsUpdateForm(): FormGroup {
    return this.updateFormGroup.get('seats') as FormGroup;
  }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      color: new FormGroup({
        name: new FormControl('', [Validators.required]),
        hexCode: new FormControl('', [
          Validators.required,
          Validators.pattern(/^#([0-9a-fA-F]{6})$/),
        ]),
      }),
      brand: new FormGroup({
        name: new FormControl('', [Validators.required]),
        logo: new FormControl('', [Validators.required]),
        founding: new FormControl(1970, [
          Validators.required,
          Validators.min(1800),
          Validators.max(new Date().getFullYear()),
        ]),
        holdingId: new FormControl('', [Validators.required]),
      }),
      type: new FormGroup({
        type: new FormControl('', [Validators.required]),
      }),
      engine: new FormGroup({
        engineType: new FormControl('', [Validators.required]),
      }),
      fuel: new FormGroup({
        fuelType: new FormControl('', [Validators.required]),
      }),
      doors: new FormGroup({
        quantity: new FormControl(0, [Validators.required, Validators.min(1)]),
      }),
      seats: new FormGroup({
        quantity: new FormControl(0, [Validators.required, Validators.min(1)]),
      }),
      holding: new FormGroup({
        name: new FormControl('', [Validators.required]),
        logo: new FormControl('', [Validators.required]),
        founding: new FormControl(1970, [
          Validators.required,
          Validators.min(1800),
          Validators.max(new Date().getFullYear()),
        ]),
      }),
    });
    this.updateFormGroup = new FormGroup({
      brand: new FormGroup({
        name: new FormControl(''),
        logo: new FormControl(''),
        founding: new FormControl(1970),
        holdingId: new FormControl(''),
      }),
      type: new FormGroup({
        type: new FormControl(''),
      }),
      engine: new FormGroup({
        engineType: new FormControl(''),
      }),
      fuel: new FormGroup({
        fuelType: new FormControl(''),
      }),
      doors: new FormGroup({
        quantity: new FormControl(0),
      }),
      seats: new FormGroup({
        quantity: new FormControl(0),
      }),
      color: new FormGroup({
        name: new FormControl(''),
        hexCode: new FormControl(''),
      }),
      holding: new FormGroup({
        name: new FormControl(''),
        logo: new FormControl(''),
        founding: new FormControl(1970),
      }),
    });
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
    if (!this.selectedColor || this.colorUpdateForm.invalid) return;

    const updatedColor: Color = {
      ...this.selectedColor,
      ...this.colorUpdateForm.value,
    };

    this.vehiclesService.updateColor(updatedColor).subscribe({
      next: () => {
        this.selectedColor = null;
        this.loadAllColors();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectColor(item: Color): void {
    this.selectedColor = { ...item };
    this.colorUpdateForm.patchValue({
      name: item.name,
      hexCode: item.hexCode,
    });
  }
  deleteColor(id?: string): void {
    const colorId = id ?? this.selectedColor?.id;
    if (!colorId) return;
    this.vehiclesService.deleteColor(colorId).subscribe({
      next: () => {
        this.selectedColor = null;
        this.loadAllColors();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  createColor(): void {
    if (this.colorForm.invalid) {
      this.errorMessageCreate = 'Please fill in all color fields correctly.';
      return;
    }
    const color: Color = {
      id: '',
      ...this.colorForm.value,
    };
    this.vehiclesService.createColor(color).subscribe({
      next: () => {
        this.loadAllColors();
        this.colorForm.reset({
          name: '',
          hexCode: '',
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Default error message';
      },
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
    if (!this.selectedDoors || this.doorsUpdateForm.invalid) return;

    const updatedDoors: Doors = {
      ...this.selectedDoors,
      ...this.doorsUpdateForm.value,
    };

    this.vehiclesService.updateDoors(updatedDoors).subscribe({
      next: () => {
        this.selectedDoors = null;
        this.loadAllDoors();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectDoors(item: Doors): void {
    this.selectedDoors = { ...item };
    this.doorsUpdateForm.patchValue({
      quantity: item.quantity,
    });
  }
  deleteDoors(id?: string): void {
    const doorsId = id ?? this.selectedDoors?.id;
    if (!doorsId) return;
    this.vehiclesService.deleteDoors(doorsId).subscribe({
      next: () => {
        this.selectedDoors = null;
        this.loadAllDoors();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  loadAllDoors(): void {
    this.vehiclesService.getDoors().subscribe((data: Doors[]) => {
      this.doors = data;
    });
  }
  createDoors(): void {
    if (this.doorsForm.invalid) {
      this.errorMessageCreate = 'Please fill in all doors fields correctly.';
      return;
    }
    const doors: Doors = {
      id: '',
      ...this.doorsForm.value,
    };
    this.vehiclesService.createDoors(doors).subscribe({
      next: () => {
        this.loadAllDoors();
        this.doorsForm.reset({
          quantity: 0,
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Default error message';
      },
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
    if (!this.selectedEngine || this.engineUpdateForm.invalid) return;

    const updatedEngine: Engine = {
      ...this.selectedEngine,
      ...this.engineUpdateForm.value,
    };

    this.vehiclesService.updateEngine(updatedEngine).subscribe({
      next: () => {
        this.selectedEngine = null;
        this.loadAllEngines();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectEngine(item: Engine): void {
    this.selectedEngine = { ...item };
    this.engineUpdateForm.patchValue({
      engineType: item.engineType,
    });
  }
  deleteEngine(id?: string): void {
    const engineId = id ?? this.selectedEngine?.id;
    if (!engineId) return;
    this.vehiclesService.deleteEngine(engineId).subscribe({
      next: () => {
        this.selectedEngine = null;
        this.loadAllEngines();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  loadAllEngines(): void {
    this.vehiclesService.getEngines().subscribe((data: Engine[]) => {
      this.engines = data;
    });
  }
  createEngine(): void {
    if (this.engineForm.invalid) {
      this.errorMessageCreate = 'Please fill in all engine fields correctly.';
      return;
    }
    const engine: Engine = {
      id: '',
      ...this.engineForm.value,
    };
    this.vehiclesService.createEngine(engine).subscribe({
      next: () => {
        this.loadAllEngines();
        this.engineForm.reset({
          engineType: '',
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Default error message';
      },
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
    if (!this.selectedFuel || this.fuelUpdateForm.invalid) return;

    const updatedFuel: Fuel = {
      ...this.selectedFuel,
      ...this.fuelUpdateForm.value,
    };

    this.vehiclesService.updateFuel(updatedFuel).subscribe({
      next: () => {
        this.selectedFuel = null;
        this.loadAllFuels();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectFuel(item: Fuel): void {
    this.selectedFuel = { ...item };
    this.fuelUpdateForm.patchValue({
      fuelType: item.fuelType,
    });
  }
  deleteFuel(id?: string): void {
    const fuelId = id ?? this.selectedFuel?.id;
    if (!fuelId) return;
    this.vehiclesService.deleteFuel(fuelId).subscribe({
      next: () => {
        this.selectedFuel = null;
        this.loadAllFuels();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  loadAllFuels(): void {
    this.vehiclesService.getFuels().subscribe((data: Fuel[]) => {
      this.fuels = data;
    });
  }
  createFuel(): void {
    if (this.fuelForm.invalid) {
      this.errorMessageCreate = 'Please fill in all fuel fields correctly.';
      return;
    }
    const fuel: Fuel = {
      id: '',
      ...this.fuelForm.value,
    };
    this.vehiclesService.createFuel(fuel).subscribe({
      next: () => {
        this.loadAllFuels();
        this.fuelForm.reset({
          fuelType: '',
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Default error message';
      },
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
    if (!this.selectedHolding || this.holdingUpdateForm.invalid) return;

    const updatedHolding: Holding = {
      ...this.selectedHolding,
      ...this.holdingUpdateForm.value,
    };

    this.vehiclesService.updateHolding(updatedHolding).subscribe({
      next: () => {
        this.selectedHolding = null;
        this.loadAllHoldings();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectHolding(item: Holding): void {
    this.selectedHolding = { ...item };
    this.holdingUpdateForm.patchValue({
      name: item.name,
      logo: item.logo,
      founding: item.founding,
    });
  }
  deleteHolding(id?: string): void {
    const holdingId = id ?? this.selectedHolding?.id;
    if (!holdingId) return;
    this.vehiclesService.deleteHolding(holdingId).subscribe({
      next: () => {
        this.selectedHolding = null;
        this.loadAllHoldings();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  loadAllHoldings(): void {
    this.vehiclesService.getHoldings().subscribe((data: Holding[]) => {
      this.holdings = data;
    });
  }

  createHolding(): void {
    if (this.holdingForm.invalid) {
      this.errorMessageCreate = 'Please fill in all holding fields correctly.';
      return;
    }
    const holding: Holding = {
      id: '',
      ...this.holdingForm.value,
    };
    this.vehiclesService.createHolding(holding).subscribe({
      next: () => {
        this.loadAllHoldings();
        this.holdingForm.reset({
          name: '',
          logo: '',
          founding: 1970,
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Failed to create holding.';
      },
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
    if (!this.selectedSeats || this.seatsUpdateForm.invalid) return;

    const updatedSeats: Seats = {
      ...this.selectedSeats,
      ...this.seatsUpdateForm.value,
    };

    this.vehiclesService.updateSeats(updatedSeats).subscribe({
      next: () => {
        this.selectedSeats = null;
        this.loadAllSeats();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectSeats(item: Seats): void {
    this.selectedSeats = { ...item };
    this.seatsUpdateForm.patchValue({
      quantity: item.quantity,
    });
  }
  deleteSeats(id?: string): void {
    const seatsId = id ?? this.selectedSeats?.id;
    if (!seatsId) return;
    this.vehiclesService.deleteSeats(seatsId).subscribe({
      next: () => {
        this.selectedSeats = null;
        this.loadAllSeats();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  loadAllSeats(): void {
    this.vehiclesService.getSeats().subscribe((data: Seats[]) => {
      this.seats = data;
    });
  }
  createSeats(): void {
    if (this.seatsForm.invalid) {
      this.errorMessageCreate = 'Please fill in all seats fields correctly.';
      return;
    }
    const seats: Seats = {
      id: '',
      ...this.seatsForm.value,
    };
    this.vehiclesService.createSeats(seats).subscribe({
      next: () => {
        this.loadAllSeats();
        this.seatsForm.reset({
          quantity: 0,
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Default error message';
      },
    });
  }

  fetchTypeByName(): void {
    this.selectedType =
      this.types.find((type) =>
        type.type.toLowerCase().includes(this.searchedTypeName.toLowerCase())
      ) ?? null;
  }
  updateType(): void {
    if (!this.selectedType || this.typeUpdateForm.invalid) return;

    const updatedType: Type = {
      ...this.selectedType,
      ...this.typeUpdateForm.value,
    };

    this.vehiclesService.updateType(updatedType).subscribe({
      next: () => {
        this.selectedType = null;
        this.loadAllTypes();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  createType(): void {
    if (this.typeForm.invalid) {
      this.errorMessageCreate = 'Please fill in all type fields correctly.';
      return;
    }
    const type: Type = {
      id: '',
      ...this.typeForm.value,
    };
    this.vehiclesService.createType(type).subscribe({
      next: () => {
        this.loadAllTypes();
        this.typeForm.reset({
          type: '',
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectType(item: Type): void {
    this.selectedType = { ...item };
    this.typeUpdateForm.patchValue({
      type: item.type,
    });
  }
  deleteType(id?: string): void {
    const typeId = id ?? this.selectedType?.id;
    if (!typeId) return;
    this.vehiclesService.deleteType(typeId).subscribe({
      next: () => {
        this.selectedType = null;
        this.loadAllTypes();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
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
    if (!this.selectedBrand || this.brandUpdateForm.invalid) return;

    const updatedBrand: Brand = {
      ...this.selectedBrand,
      ...this.brandUpdateForm.value,
    };

    this.vehiclesService.updateBrand(updatedBrand).subscribe({
      next: () => {
        this.selectedBrand = null;
        this.loadAllBrands();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  selectBrand(item: Brand): void {
    this.selectedBrand = { ...item };
    this.brandUpdateForm.patchValue({
      name: item.name,
      logo: item.logo,
      founding: item.founding,
      holdingId: item.holdingId,
    });
  }
  deleteBrand(id?: string): void {
    const brandId = id ?? this.selectedBrand?.id;
    if (!brandId) return;
    this.vehiclesService.deleteBrand(brandId).subscribe({
      next: () => {
        this.selectedBrand = null;
        this.loadAllBrands();
        this.errorMessageUpdate = null;
      },
      error: (error) => {
        this.errorMessageUpdate =
          error?.error?.message || 'Default error message';
      },
    });
  }
  loadAllBrands(): void {
    this.vehiclesService.getBrands().subscribe((data: Brand[]) => {
      this.brands = data;
    });
  }

  createBrand(): void {
    if (this.brandForm.invalid) {
      this.errorMessageCreate = 'Please fill in all brand fields correctly.';
      return;
    }
    const brand: Brand = {
      id: '',
      ...this.brandForm.value,
    };
    this.vehiclesService.createBrand(brand).subscribe({
      next: () => {
        this.loadAllBrands();
        this.brandForm.reset({
          name: '',
          logo: '',
          founding: 1970,
          holdingId: '',
        });
        this.errorMessageCreate = null;
      },
      error: (error) => {
        this.errorMessageCreate =
          error?.error?.message || 'Failed to create brand.';
      },
    });
  }

  onHoldingSelectedForBrand(holding: Holding): void {
    if (this.selectedBrand) {
      this.selectedBrand.holdingId = holding.id;
    }
  }
  onTabChange(): void {
    this.errorMessageCreate = null;
    this.errorMessageUpdate = null;
  }
}
