import { Routes } from '@angular/router';
import { VehiclesComponent } from './Web/vehicles/vehicles.component';
import { VehicleDetailsComponent } from './Web/vehicles/vehicle-details/vehicle-details.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'vehicles',
    pathMatch: 'full',
  },
  {
    path: 'vehicles',
    component: VehiclesComponent,
  },
  {
    path: 'vehicles/:id',
    component: VehicleDetailsComponent,
  },
];
