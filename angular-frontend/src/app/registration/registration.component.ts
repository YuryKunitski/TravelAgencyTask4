import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user/user.service';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerForm: FormGroup;
  invalidRegisterForm: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }
  // user.username = registerForm.controls.username.value;
  // user.password = registerForm.controls.password.value;

  confirmPasswords(group: FormGroup): boolean { // here we have the 'passwords' group
  let pass = group.get('password').value;
  let confirmPass = group.get('confirmPassword').value;

  return pass === confirmPass ? true : false;     
}

    onSubmitReg(){
      if (this.registerForm.invalid || !this.confirmPasswords(this.registerForm) ) {
        alert('Invalid registration form')
        return;
      }
  
      const body = new HttpParams()
      .set('grant_type', 'client_credentials');  // GRANT_TYPE = "client_credentials";
      
      this.userService.login(body.toString()).subscribe(data => {
   
        window.sessionStorage.setItem('token', JSON.stringify(data));
  
        this.userService.createMember(this.userService.getUserByForm(this.registerForm))
        .then(user => {
          
            window.sessionStorage.setItem('role', user.role.toString());
            window.sessionStorage.setItem('login', user.username);
        
            console.log("Access token from session - " + window.sessionStorage.getItem('token'));
            console.log("User name from session - " + window.sessionStorage.getItem('login'))
            console.log("User role from session - " + window.sessionStorage.getItem('role'));
        
            this.router.navigate(['']);
          }, error => {
          alert(error.message)
        });
      }, error => {
          this.invalidRegisterForm = true
          alert(error.message)
          alert(error.error_description)
      });
  
    }

}