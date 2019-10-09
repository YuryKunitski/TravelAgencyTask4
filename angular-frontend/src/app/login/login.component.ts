import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { AuthenticationService } from '../service/authentication.service';
import { HttpParams } from '@angular/common/http';
import { UserService } from '../user/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup;
  invalidLogin: boolean = false

  constructor(private router: Router,
              private formBuilder: FormBuilder,
              private userService: UserService) { }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    const body = new HttpParams()
      .set('username', this.loginForm.controls.username.value)
      .set('password', this.loginForm.controls.password.value)
      .set('grant_type', 'password');

    this.userService.login(body.toString()).subscribe(data => {
      
      console.log("JSON.stringify(data) - "+JSON.stringify(data) +"------------------------------");

      window.sessionStorage.setItem('token', JSON.stringify(data));

      console.log(window.sessionStorage.getItem('token')+"------------------------------");

      this.router.navigate(['']);
    }, error => {
      this.invalidLogin = true
        alert(error.message)
        alert(error.error_description)
    });
  }            

  ngOnInit() {
    window.sessionStorage.removeItem('token');
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required]
    });
  }

  // checkLogin() {
  //   (this.loginservice.authenticate(this.username, this.password).subscribe(
  //     data => {
  //       this.router.navigate([''])
  //       this.invalidLogin = false
  //       },
  //     error => {
  //       this.invalidLogin = true

  //       }
  //     )
  //   );
  // }
}
