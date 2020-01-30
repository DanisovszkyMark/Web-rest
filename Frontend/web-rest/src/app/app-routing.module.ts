import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegPageComponent } from './pages/reg-page/reg-page.component';
import { UsersPageComponent } from './pages/users-page/users-page.component'
import { ActivationPageComponent } from './pages/activation-page/activation-page.component';
import { ActivationSuccessfullPageComponent } from './pages/activation-successfull-page/activation-successfull-page.component';
import { ActivationFailedPageComponent } from './pages/activation-failed-page/activation-failed-page.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'registration',
    component: RegPageComponent
  },
  {
    path: 'users',
    component: UsersPageComponent
  },
  {
    path: 'activation/:activationKey',
    component: ActivationPageComponent
  },
  {
    path: 'activationSuccessfull',
    component: ActivationSuccessfullPageComponent
  },
  {
    path: 'activationFailed',
    component: ActivationFailedPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
