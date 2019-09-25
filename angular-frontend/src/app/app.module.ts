import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CountryComponent } from './country/country.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import { CountryService } from './country/country.service';
import { UserComponent } from './user/user.component';
import { HotelComponent } from './hotel/hotel.component';
import { HeaderComponent } from './nav/header/header.component';
import { FooterComponent } from './nav/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    UserComponent,
    HotelComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [CountryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
