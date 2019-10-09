import { Injectable } from '@angular/core';
import { User } from '../user/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  authenticate(username, password) {
    return this.httpClient.post<any>(this.baseUrl + '/oauth/token', {username,password}).pipe(
       map(
         userData => {
          sessionStorage.setItem('username',username);
          let tokenStr= 'Bearer '+userData.token;
          sessionStorage.setItem('token', tokenStr);
          return userData;
         }
       )
  
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}
