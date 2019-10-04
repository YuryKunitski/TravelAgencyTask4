import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CountryComponent } from './country/country.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import { CountryService } from './country/country.service';
import { HotelService } from './hotel/hotel.service';
import { TourService } from './tour/tour.service';
// import { UserComponent } from './user/user.component';
import { HotelComponent } from './hotel/hotel.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigatorComponent } from './navigator/navigator.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { CustomTableComponent } from './custom-table/custom-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { TourComponent } from './tour/tour.component';
import { TourSearchFormComponent } from './tour-search-form/tour-search-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    // UserComponent,
    HotelComponent,
    NavigatorComponent,
    CustomTableComponent,
    TourComponent,
    TourSearchFormComponent
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
    MatSortModule
  ],
  providers: [CountryService, HotelService, TourService],
  bootstrap: [AppComponent]
})
export class AppModule { }
