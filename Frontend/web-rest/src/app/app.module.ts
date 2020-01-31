import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { FooterComponent } from './components/footer/footer.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegPageComponent } from './pages/reg-page/reg-page.component';
import { RegSuccessfullComponent } from './components/reg-successfull/reg-successfull.component';
import { UsersPageComponent } from './pages/users-page/users-page.component';

import { MatDialogModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivationSuccessfullPageComponent } from './pages/activation-successfull-page/activation-successfull-page.component';
import { ActivationFailedPageComponent } from './pages/activation-failed-page/activation-failed-page.component';
import { ActivationPageComponent } from './pages/activation-page/activation-page.component';
import { MenuComponent } from './components/menu/menu.component';
import { UsersComponent } from './components/users/users.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { BlockPageComponent } from './pages/block-page/block-page.component';
import { SupportPageComponent } from './pages/support-page/support-page.component';
import { IgxListModule } from 'igniteui-angular';
import { AreYouSureComponent } from './components/are-you-sure/are-you-sure.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LoginPageComponent,
    RegPageComponent,
    RegSuccessfullComponent,
    UsersPageComponent,
    ActivationSuccessfullPageComponent,
    ActivationFailedPageComponent,
    ActivationPageComponent,
    MenuComponent,
    UsersComponent,
    ProfilePageComponent,
    BlockPageComponent,
    SupportPageComponent,
    AreYouSureComponent,
    UpdateUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    IgxListModule
  ],
  entryComponents: [RegSuccessfullComponent, AreYouSureComponent, UpdateUserComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
