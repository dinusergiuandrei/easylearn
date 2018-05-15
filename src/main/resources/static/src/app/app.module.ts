import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { MaterialModule } from './modules/material.module';
import { LoadingScreenComponent } from './shared/components/loading-screen/loading-screen.component';
import { LoadingScreenService } from './services/loading-screen.service';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { LandingComponent } from './components/public/landing/landing.component';
import { LoginComponent } from './components/public/login/login.component';
import { ForgotComponent } from './components/public/forgot/forgot.component';
import { RegisterComponent } from './components/public/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoadingScreenComponent,
    NavbarComponent,
    FooterComponent,
    LandingComponent,
    LoginComponent,
    ForgotComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule
  ],
  providers: [
    LoadingScreenService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
