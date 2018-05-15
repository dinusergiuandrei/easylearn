import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { LandingComponent } from './_components/public/landing/landing.component';
import { NavbarComponent } from './_shared/components/navbar/navbar.component';
import { FooterComponent } from './_shared/components/footer/footer.component';
import { LoginComponent } from './_components/public/login/login.component';
import { ForgotComponent } from './_components/public/forgot/forgot.component';
import { RegisterComponent } from './_components/public/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    ForgotComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
