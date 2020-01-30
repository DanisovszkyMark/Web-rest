import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { MatDialog } from '@angular/material';
import { RegSuccessfullComponent } from 'src/app/components/reg-successfull/reg-successfull.component';

@Component({
  selector: 'app-reg-page',
  templateUrl: './reg-page.component.html',
  styleUrls: ['./reg-page.component.scss']
})
export class RegPageComponent implements OnInit {
  public showPassword: boolean;

  public showError: boolean;
  public errorMessage: string;

  private registrationForm: FormGroup;

  constructor(
    private authService: AuthServiceService,
    private dialog: MatDialog
  ) {
    this.registrationForm = new FormGroup({
      usernameControl: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      emailControl: new FormControl('', [Validators.required, Validators.email, Validators.minLength(3), Validators.maxLength(150)]),
      passwordControl: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      passwordAgainControl: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)])
    });
   }

  ngOnInit() {
  }

  submitReg(){
    const{ usernameControl, emailControl, passwordControl, passwordAgainControl} = this.registrationForm.value;
    this.showError = false;

    if(this.registrationForm.invalid){
      this.showError = true;

      if(this.registrationForm.get('usernameControl').invalid){
        this.errorMessage = "Invalid Username. Username must be at least 3, max 50 characters long.";
      }
      else if(this.registrationForm.get('emailControl').invalid){
        this.errorMessage = "Invalid Email address.";
      }
      else if(this.registrationForm.get('passwordControl').invalid){
        this.errorMessage = "Invalid Password. Password must be at least 3, max 50 characters long.";
      }
      else{
        this.errorMessage = "Invalid Password again. Password again must be at least 3, max 50 characters long.";
      }

      return;
    }

    if(passwordControl != passwordAgainControl){
      this.showError = true;
      this.errorMessage = "Passwords doesn't match.";
      return;
    }

      this.authService.registration(usernameControl, emailControl, passwordControl).subscribe((response) => {
        if(response == 201){
          this.dialog.open(RegSuccessfullComponent);
        }
      }, (error) => {
        this.showError = true;

        if(error.status == 409){
          this.errorMessage = "Username or Email already in use.";
        }
        else {
          this.errorMessage = "Something went wrong! (" + error.status + ")";
        }
      });
  }

}
