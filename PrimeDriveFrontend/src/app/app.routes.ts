import { Routes } from '@angular/router';
import { VehiclesComponent } from './Web/vehicles/vehicles.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: VehiclesComponent,
  },
];
