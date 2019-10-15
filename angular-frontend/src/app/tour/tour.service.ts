import { Injectable } from '@angular/core';
import { Tour } from './tour';

import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';   
import { TourForm } from './tour-form';
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

  getToursByCriteria(tourForm: TourForm):  Promise<Tour[]> {

    let params = new HttpParams({
      fromObject : {
        requiredParam: 'requiredParam'
      }
    });

    if(tourForm.minCost != null){
      params = params.append('minCost', tourForm.minCost.toString());
    }
    if(tourForm.minDuration != null){
      params = params.append('minDuration', tourForm.minDuration.toString());
    }
    if(tourForm.minStars != null){
      params = params.append('minStars', tourForm.minStars.toString());
    }
    if(tourForm.minDate != null){
      params = params.append('minDate', tourForm.minDate.toString());
    }
    if(tourForm.tour_type != null){
      params = params.append('tourType', tourForm.tour_type.toString());
    }
    if(tourForm.maxCost != null){
      params = params.append('maxCost', tourForm.maxCost.toString());
    }
    if(tourForm.maxDuration != null){
      params = params.append('maxDuration', tourForm.maxDuration.toString());
    }
    if(tourForm.maxStars != null){
      params = params.append('maxStars', tourForm.maxStars.toString());
    }
    if(tourForm.maxDate != null){
      params = params.append('maxDate', tourForm.maxDate.toString());
    }
    if(tourForm.countryName != null){
      params = params.append('countryName', tourForm.countryName.toString());
    }    

    const headers = {};
    headers['tourType'] = tourForm.tour_type;
    headers['minCost'] = tourForm.minCost;
    const httpHeaders = new HttpHeaders(headers);

    return this.http.get(this.baseUrl + '/tour/get_all_by_criteria', {params})
      .toPromise()
      .catch(this.handleError);
  }

  getToursByUserId(id: string):  Promise<Tour[]> {
    return this.http.get(this.baseUrl + '/tour/get_all_by_user_id?id='+id)
      .toPromise()
    //   .then(response => response.json() as Tour[])
      .catch(this.handleError);
  }

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

  private handleError(error: HttpErrorResponse): Promise<any> {
    console.error('Some error occured', error);
    // Observable.throw(error.message || error).toPromise();
    
    if(error.message){
    // alert(error.message);
    alert(error.message)
    }
    return Promise.reject(error.message || error);
  }

}