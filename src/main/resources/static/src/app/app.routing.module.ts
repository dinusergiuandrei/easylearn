import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/public/login/login.component';
import { ForgotComponent } from './components/public/forgot/forgot.component';
import { RegisterComponent } from './components/public/register/register.component';
import { SignComponent } from './components/public/sign/sign.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: SignComponent,
    children: [{ path: '', component: LoginComponent }] },
  { path: 'forgot', component: ForgotComponent },
  { path: 'register', component: SignComponent,
    children: [{ path: '', component: RegisterComponent }] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
