import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/public/sign/login/login.component';
import { ForgotComponent } from './components/public/sign/forgot/forgot.component';
import { RegisterComponent } from './components/public/sign/register/register.component';
import { SignComponent } from './components/public/sign/sign.component';
import { TopComponent } from './components/pages/top/top.component';
import { Error403Component } from './components/pages/errors/error403/error403.component';
import { Error404Component } from './components/pages/errors/error404/error404.component';
import { AboutUsComponent } from './components/pages/about-us/about-us.component';
import { ProblemComponent } from './components/pages/problem/problem.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { UserDashboardComponent } from './components/pages/user-dashboard/user-dashboard.component';
import { ProblemsCategoryComponent } from './components/pages/problems-category/problems-category.component';
import { AddProblemComponent } from './components/pages/add-problem/add-problem.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: SignComponent,
    children: [{ path: '', component: LoginComponent }] },
  { path: 'forgot', component: SignComponent,
    children: [{ path: '', component: ForgotComponent }] },
  { path: 'register', component: SignComponent,
    children: [{ path: '', component: RegisterComponent }] },
  { path: 'top', component: TopComponent},
  { path: 'about-us', component: AboutUsComponent },
  { path: 'problem', component: ProblemComponent },
  { path: 'problems-category/:id', component: ProblemsCategoryComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'add-problem', component: AddProblemComponent },
  { path: '403', component: Error403Component},
  { path: '404', component: Error404Component },
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
