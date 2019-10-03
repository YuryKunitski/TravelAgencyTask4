import { Injectable } from '@angular/core';
import { Tour } from './tour';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';   

@Injectable()
export class TourService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getTours():  Promise<Tour[]> {
    return this.http.get(this.baseUrl + '/tour/get_all')
      .toPromise()
    //   .then(response => response.json() as Tour[])
      .catch(this.handleError);
  }

  getTour(id: string):  Promise<Tour> {
    return this.http.get(this.baseUrl + '/tour/get_by_id?id='+id)
      .toPromise()
    //   .then(response => response.json() as Tour[])
      .catch(this.handleError);
  }

  getToursByUserId(id: string):  Promise<Tour[]> {
    return this.http.get(this.baseUrl + '/tour/get_all_by_user_id?id='+id)
      .toPromise()
    //   .then(response => response.json() as Tour[])
      .catch(this.handleError);
  }

//   getToursByCriteria(tourForm: TourForm):  Promise<Tour[]> {
//     return this.http.get(this.baseUrl + '/tour/get_all_by_criteria')
//       .toPromise()
//     //   .then(response => response.json() as Tour[])
//       .catch(this.handleError);
//   }

  createTour(tourData: Tour): Promise<Tour> {
    return this.http.post(this.baseUrl + '/tour', tourData)
      .toPromise()
    // then(response => response.json() as Tour)
      .catch(this.handleError);
  }

  updateTour(tourData: Tour): Promise<Tour> {
    return this.http.put(this.baseUrl + '/tour?id=' + tourData.id, tourData)
      .toPromise()
    //   .then(response => response.json() as Tour)
      .catch(this.handleError);
  }

  deleteTour(id: string): Promise<any> {
    return this.http.delete(this.baseUrl + '/tour?id=' + id)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
    // return Observable.throw(error.status).toPromise();
  }

}