import {Routes} from '@angular/router';
import {DetailComponent} from './detailFahrzeug/detail.component';
import {OverviewComponent} from './overviewFahrzeuge/overview.component';

export const routes: Routes = [
  { path: '', redirectTo: 'fahrzeuge', pathMatch: 'full' },
  {path: 'fahrzeuge', component: OverviewComponent},
  {path: 'fahrzeuge/:id', component: DetailComponent},
];
