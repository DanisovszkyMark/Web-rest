import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/services/user-service.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { AreYouSureComponent } from '../are-you-sure/are-you-sure.component';
import { UpdateUserComponent } from '../update-user/update-user.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  public users;

  areYouSureDialogRef: MatDialogRef<AreYouSureComponent>;
  updateUserDialogRef: MatDialogRef<UpdateUserComponent>;

  constructor(
    public userService: UserServiceService,
    public authService: AuthServiceService,
    public router: Router,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.refreshDatas();
  }

  refreshDatas(){
    this.userService.getUsers(localStorage.getItem('token')).subscribe((response) => {
      this.users = response;
    },(error) => {
      if(error.status == 401){
        this.router.navigateByUrl('/login');
      }
      else{ //lehet túl általános
        this.router.navigateByUrl('/login');
      }
    });
  }
  
  onBlock(item){
    const user = this.users[item.index];

    const dialogRef = this.dialog.open(AreYouSureComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if(result == true){
        this.authService.block(localStorage.getItem('token'), user.id).subscribe((response) =>{
          if(response == 200){
            this.refreshDatas();
          }
        }, (error) => {
          console.log(error);
        });
      }
    });
  }

  onDelete(item){
    const user = this.users[item.index];

    const dialogRef = this.dialog.open(AreYouSureComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if(result == true){
        this.userService.deleteUser(localStorage.getItem('token'), user.id).subscribe((response) =>{
          if(response == 200){
            this.refreshDatas();
          }
        }, (error) => {
          console.log(error);
        });
      }
    });
  }

  onUpdate(item){
    const user = this.users[item.index];

    this.updateUserDialogRef = this.dialog.open(UpdateUserComponent, {
      data: {
        id: user.id,
        username: user.username,
        email: user.email,
        password: 'Password' //A jelszót nem adjuk ki backend oldaláról
      }
    });
    
    this.updateUserDialogRef.afterClosed().subscribe((result) => {
      if(result == true){
        this.refreshDatas();
      }
    });
  }
}
