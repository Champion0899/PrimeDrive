import { AdminComponent } from './Web/admin/admin.component';
import { Routes } from '@angular/router';
import { VehiclesComponent } from './Web/vehicles/vehicles.component';
import { VehicleDetailsComponent } from './Web/vehicles/vehicle-details/vehicle-details.component';
import { AdminGuard } from './Guards/admin.guard';
import { SellComponent } from './Web/sell/sell.component';

/**
 * Application route configuration for PrimeDrive.
 * Defines navigation paths for vehicle list and vehicle details views.
 *
 * Routes:
 * - '' redirects to '/vehicles'
 * - '/vehicles' displays the vehicle list
 * - '/vehicles/:id' displays details for a selected vehicle
 *
 * Author: Fatlum Epiroti & Jamie Schüpbach
 * Version: 1.0.0
 * Date: 2025-06-03
 */
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
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuard],
  },
  {
    path: 'home',
    component: VehiclesComponent,
  },
  {
    path: 'sell',
    component: SellComponent,
  },
  {
    path: 'contact',
    component: VehiclesComponent,
  },
  {
    path: 'services',
    component: VehiclesComponent,
  },
];
