import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  public showPassword: boolean;

  public showError: boolean;
  public errorMessage: string;

  public loginForm: FormGroup;

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) { 
    this.loginForm = new FormGroup({
      usernameOrEmailControl: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      passwordControl: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)])
    });
  }

  ngOnInit() {
  }

  submitLogin() {
    this.showError = false;
    const { usernameOrEmailControl, passwordControl } = this.loginForm.value;

    if(this.loginForm.invalid){
      this.showError = true;

      if(this.loginForm.get('usernameOrEmailControl').invalid){
        this.errorMessage = "Username must be at least 3, max 50 characters long.";
      }
      else if(this.loginForm.get('passwordControl').invalid){
        this.errorMessage = "Password must be at least 3, max 50 characters long.";
      }

      return;
    }
  
    this.authService.login(usernameOrEmailControl, passwordControl).subscribe((response) => {
      if((response) != null){
        this.router.navigateByUrl('/users');
      }
    }, (error) => {
      if(error.status = 404){
        this.errorMessage = "Invalid Username or Password!";
        this.showError = true;
      }
      else{
        this.errorMessage = "Something went wrong! (" + error.status + ")";
        this.showError = true;
      }
    });
  
  }

}
