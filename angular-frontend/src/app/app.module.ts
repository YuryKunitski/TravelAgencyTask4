import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CountryComponent } from './country/country.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import { CountryService } from './country/country.service';
import { HotelService } from './hotel/hotel.service';
import { TourService } from './tour/tour.service';
import { UserComponent } from './user/user.component';
import { UserService } from './user/user.service';
import { HotelComponent } from './hotel/hotel.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigatorComponent } from './navigator/navigator.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { TourComponent } from './tour/tour.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { BasicAuthInterceptService } from './service/basic-auth-intercept.service';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    UserComponent,
    HotelComponent,
    NavigatorComponent,
    TourComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
  ],
  providers: [
    CountryService,
    HotelService,
    TourService,
    UserService,
  // {
  //   provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptService, multi:true 
  // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
