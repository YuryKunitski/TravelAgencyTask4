import { Injectable } from '@angular/core';
import { Hotel } from './hotel';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';   

@Injectable()
export class HotelService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getHotels():  Promise<Hotel[]> {
    return this.http.get(this.baseUrl + '/hotel/get_all/')
      .toPromise()
    //   .then(response => response.json() as Hotel[])
      .catch(this.handleError);
  }

  createHotel(hotelData: Hotel): Promise<Hotel> {
    return this.http.post(this.baseUrl + '/hotel', hotelData)
      .toPromise()
    // then(response => response.json() as Hotel)
      .catch(this.handleError);
  }

  updateHotel(hotelData: Hotel): Promise<Hotel> {
    return this.http.put(this.baseUrl + '/hotel?id=' + hotelData.id, hotelData)
      .toPromise()
    //   .then(response => response.json() as Hotel)
      .catch(this.handleError);
  }

  deleteHotel(id: string): Promise<any> {
    return this.http.delete(this.baseUrl + '/hotel?id=' + id)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    
    if(error.message){
      alert(error.message);
      }

    return Promise.reject(error.message || error);
    // return Observable.throw(error.status).toPromise();
  }
}