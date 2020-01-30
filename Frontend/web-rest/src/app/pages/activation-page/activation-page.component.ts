import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activation-page',
  templateUrl: './activation-page.component.html',
  styleUrls: ['./activation-page.component.scss']
})
export class ActivationPageComponent implements OnInit {

  constructor(
    public authService: AuthServiceService,
    public router: Router,
    public activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.authService.activation(this.activatedRoute.snapshot.params['activationKey']).subscribe((response) => {
      if(response == 200){
        this.router.navigateByUrl("/activationSuccessfull");
      }
      else{
        this.router.navigateByUrl("/activationFailed");
      }
    },(error) => {
      this.router.navigateByUrl("/activationFailed");
    });
  }



}
