import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserServiceService } from 'src/app/services/user-service.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent implements OnInit {

  
  public errorMessage: string;
  public showError: boolean;

  public newUsername: string;
  public newEmail: string;
  public newPassword: string;

  public updateUserForm: FormGroup;

  constructor(
    public userService: UserServiceService,
    public dialogRef: MatDialogRef<UpdateUserComponent>, 
    @Inject(MAT_DIALOG_DATA) private data: any) {
      this.updateUserForm = new FormGroup({
        usernameControl: new FormControl('', [Validators.maxLength(50)]),
        emailControl: new FormControl('', [Validators.maxLength(50)]),
        passwordControl: new FormControl('', [Validators.maxLength(50)])
      });
  }

  ngOnInit() {
  }

  update(){

    if(this.updateUserForm.invalid){
      this.errorMessage = "Invalid.";
      this.showError = true;
      console.log('invalid');
      return;
    }

    const { usernameControl, emailControl, passwordControl } = this.updateUserForm.value;

    this.newUsername = this.data.username;
    if(usernameControl.length > 3){
      this.newUsername = usernameControl;
    }

    this.newEmail = this.data.email;
    if(emailControl.length > 3){
      this.newEmail = emailControl; //email validálása
    }

    this.newPassword = this.data.password;
    if(passwordControl.length > 3){
      this.newPassword = passwordControl;
    }

    if(passwordControl.length >= 3){
      this.userService.updateUser(localStorage.getItem('token'), this.data.id, this.newUsername, this.newEmail, this.newPassword).subscribe((result) =>{
        if(result == 200){
          this.dialogRef.close(true);
        }
      }, (error) => {
        console.log(error);
        this.dialogRef.close(false);
      });
    }
    else{
      this.userService.updateUserWithoutPassword(localStorage.getItem('token'), this.data.id, this.newUsername, this.newEmail).subscribe((result) =>{
        if(result == 200){
          this.dialogRef.close(true);
        }
      }, (error) => {
        console.log(error);
        this.dialogRef.close(false);
      });
    }
  }

}
