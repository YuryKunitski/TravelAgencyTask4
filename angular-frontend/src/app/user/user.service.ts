import { Injectable } from '@angular/core';
import { User } from './user';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/index';   
// import {ApiResponse} from "../model/api.response";

@Injectable()
export class UserService {
  private username='client-id';
  private password='client-secret';
  private baseUrl = 'http://localhost:8080/';

  constructor(private http:HttpClient) {}

  login(loginPayload) {
    const headers = {
      'Authorization': 'Basic ' + btoa('client-id:client-secret'),
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post(this.baseUrl + 'oauth/token', loginPayload, {headers});
  }

  // getUsers() {
  //   return this.http.get(this.baseUrl + 'user?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  // }

  getUsers(): Promise<User[]> {
    
    return this.http.get(this.baseUrl + 'user/get_all?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token)
      .toPromise()
    //   .then(response => response.json() as user[])
      .catch(this.handleError);
  }

  getUserById(id: string) {
    return this.http.get(this.baseUrl + 'user/get_by_id?id=' + id + '&access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  getUserByName(username: string) {
    return this.http.get(this.baseUrl + 'user/get_by_username?username=' + username + '&access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  createMember(userData: User): Promise<User> {
    return this.http.post(this.baseUrl + 'user/add_member?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, userData)
      .toPromise()
    // then(response => response.json() as user)
      .catch(this.handleError);
  }

createAdmin(userData: User): Promise<User> {
    return this.http.post(this.baseUrl + 'user/add_admin?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, userData)
      .toPromise()
    // then(response => response.json() as user)
      .catch(this.handleError);
  }

  updateUser(userData: User): Promise<User> {
    return this.http.put(this.baseUrl + 'user?id=' + userData.id + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, userData)
      .toPromise()
    //   .then(response => response.json() as user)
      .catch(this.handleError);
  }

  deleteUser(id: string): Promise<any> {
    return this.http.delete(this.baseUrl + 'user?id=' + id +'?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }

}