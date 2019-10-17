import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CountryComponent } from './country/country.component';
import { HotelComponent } from './hotel/hotel.component';
import { TourComponent } from './tour/tour.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import{ RegistrationComponent } from './registration/registration.component';
import { SigninComponent } from './signin/signin.component' 

const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGaurdService] },
  { path: 'countries', component: CountryComponent, canActivate:[AuthGaurdService] },
  { path: 'hotels', component: HotelComponent,canActivate:[AuthGaurdService] },
  { path: 'socialSignIn', component: SigninComponent}, 
  { path: '', component: TourComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
