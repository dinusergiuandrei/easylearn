import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { MaterialModule } from './modules/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormBuilder, FormsModule } from '@angular/forms';

// Services
import { LoadingScreenService } from './services/loading-screen.service';

// Components
import { LoadingScreenComponent } from './shared/components/loading-screen/loading-screen.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { Error404Component } from './components/pages/error404/error404.component';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/public/login/login.component';
import { ForgotComponent } from './components/public/forgot/forgot.component';
import { RegisterComponent } from './components/public/register/register.component';
import { AboutUsComponent } from './components/pages/about-us/about-us.component';
import { SignComponent } from './components/public/sign/sign.component';
import { TopComponent } from './components/pages/top/top.component';
import { TestimonialsComponent } from './components/public/testimonials/testimonials.component';
import { LatestComponent} from './components/public/latest/latest.component';
import { YourlatestComponent } from './components/public/yourlatest/yourlatest.component';
import { ProblemComponent } from './components/pages/problem/problem.component';
import { MonacoEditorModule } from 'ngx-monaco-editor';
import { SolutionsComponent } from './components/public/solutions/solutions.component';
import { UserProfileComponent } from './components/pages/user-profile/user-profile.component';

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
    RegisterComponent,
    SignComponent,
    TopComponent,
    Error404Component,
    TestimonialsComponent,
    LatestComponent,
    YourlatestComponent,
    ProblemComponent,
    SolutionsComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    MonacoEditorModule.forRoot(),
  ],
  providers: [
    LoadingScreenService,
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
