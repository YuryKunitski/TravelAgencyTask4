import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CountryComponent } from './country/country.component';
import { HotelComponent } from './hotel/hotel.component';
const routes: Routes = [
  { path: 'countries', component: CountryComponent },
  { path: 'hotels', component: HotelComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
