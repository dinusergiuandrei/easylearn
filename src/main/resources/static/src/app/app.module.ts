import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { MaterialModule } from './modules/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormBuilder, FormsModule } from '@angular/forms';

import { LoadingScreenComponent } from './shared/components/loading-screen/loading-screen.component';
import { LoadingScreenService } from './services/loading-screen.service';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/public/login/login.component';
import { ForgotComponent } from './components/public/forgot/forgot.component';
import { RegisterComponent } from './components/public/register/register.component';
import { AboutUsComponent } from './components/pages/about-us/about-us.component';

@NgModule({
  declarations: [
    AppComponent,
    LoadingScreenComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    AboutUsComponent,
    LoginComponent,
    ForgotComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  providers: [
    LoadingScreenService,
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
