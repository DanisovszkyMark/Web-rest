import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor(
    public authService: AuthServiceService,
    public router: Router
  ) { }

  ngOnInit() {
  }

  profileClick(){
    this.router.navigateByUrl('/profile');
  }

  blockClick(){
    this.router.navigateByUrl('/block');
  }

  supportClick(){
    this.router.navigateByUrl('/support');
  }

  logoutClick(){
    this.authService.logout(localStorage.getItem("token")).subscribe((response) => {
      localStorage.removeItem("token");
      this.router.navigateByUrl('/login');
    }, (error) => {
      console.log("sikertelen kijelentkezés");
    });
  }
}
