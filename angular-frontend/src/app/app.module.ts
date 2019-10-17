import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CountryComponent } from './country/country.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
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
import { ReactiveFormsModule } from "@angular/forms";
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2OrderModule } from 'ng2-order-pipe';
import { RegistrationComponent } from './registration/registration.component';
import { 
  SocialLoginModule,
   AuthServiceConfig,
    GoogleLoginProvider,
     FacebookLoginProvider }
      from "angular-6-social-login";
import { SigninComponent } from './signin/signin.component';
import { VkontakteLoginProvider } from 'angular-6-social-login-v2';

// Configs 
export function getAuthServiceConfigs() {
  let config = new AuthServiceConfig(
      [
        {
          id: FacebookLoginProvider.PROVIDER_ID,
          provider: new FacebookLoginProvider("508674179690672")
        },
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider("136328110166-8plntk6h8v6dbs308dt7122pj3jaogdf.apps.googleusercontent.com")
        },
        {
          id: VkontakteLoginProvider.PROVIDER_ID,
          provider: new VkontakteLoginProvider("7174004")
        },
      ]
  );
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    UserComponent,
    HotelComponent,
    NavigatorComponent,
    TourComponent,
    LoginComponent,
    LogoutComponent,
    RegistrationComponent,
    SigninComponent
  ],
  imports: [
    BrowserModule,
    Ng2OrderModule,
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
    NgxPaginationModule,
    SocialLoginModule
  ],
  providers: [
    CountryService,
    HotelService,
    TourService,
    UserService,
    {
      provide: AuthServiceConfig,
      useFactory: getAuthServiceConfigs
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }