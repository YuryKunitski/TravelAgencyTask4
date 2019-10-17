import { Component, OnInit } from '@angular/core';
import {
  AuthService,
  FacebookLoginProvider,
  GoogleLoginProvider
} from 'angular-6-social-login';
import { VkontakteLoginProvider } from 'angular-6-social-login-v2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  constructor(private socialAuthService: AuthService,
    private router: Router) { }

  ngOnInit() {
  }

  public socialSignIn(socialPlatform: string) {
    
    console.log("socialSignIn start - " + socialPlatform);

    let socialPlatformProvider;
    if (socialPlatform == "facebook") {
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    } else if (socialPlatform == "google") {
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    }
    else if (socialPlatform == "vkontakte") {
      socialPlatformProvider = VkontakteLoginProvider.PROVIDER_ID;
    }

    this.socialAuthService.signIn(socialPlatformProvider).then(
      (userData) => {
        window.sessionStorage.setItem('token', userData.token);
        window.sessionStorage.setItem('role', 'MEMBER');
        window.sessionStorage.setItem('login', userData.name);

        console.log("Social network token - " + userData.token)
        console.log(socialPlatform + " sign in data, userData : ", userData);
        console.log("userData.name - " + userData.name);

        this.router.navigate(['']);

      }, error => {
        console.error(error.message);
        alert(error.message)
        alert(error.error_description)
      });

    console.log("socialSignIn finish - " + socialPlatform);
  }

  signOut(): void {
    this.socialAuthService.signOut();
  }

}
