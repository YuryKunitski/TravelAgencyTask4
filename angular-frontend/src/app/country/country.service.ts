import { Injectable } from '@angular/core';
import { Country } from './country';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';   

@Injectable()
export class CountryService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) {}

  getCountries():  Promise<Country[]> {
    return this.http.get(this.baseUrl + '/country/get_all/')
      .toPromise()
    //   .then(response => response.json() as Country[])
      .catch(this.handleError);
  }

  createCountry(countryData: Country): Promise<Country> {
    return this.http.post(this.baseUrl + '/country', countryData)
      .toPromise()
    // then(response => response.json() as Country)
      .catch(this.handleError);
  }

  updateCountry(countryData: Country): Promise<Country> {
    return this.http.put(this.baseUrl + '/country?id=' + countryData.id, countryData)
      .toPromise()
    //   .then(response => response.json() as Country)
      .catch(this.handleError);
  }

  deleteCountry(id: string): Promise<any> {
    return this.http.delete(this.baseUrl + '/country?id=' + id)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}