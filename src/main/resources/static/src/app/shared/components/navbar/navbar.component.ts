import { Component, OnInit, HostListener, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../../../shared';
import { UserService } from '../../../services/user-account.service';
import { AuthService } from '../../../services/auth.service';
import { LogoutComponent } from '../../../components/pages/logout/logout.component';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy {

  logo: { name: string; path: string; };
  logged = '';
  firstButton = '';
  secondButton = '';
  private isLogged;

  public link;
  public link2;

  constructor (
    private UserService: UserService,
    private auth: AuthService,
    private router: Router
  ) {
    this.logo = {
      name: 'easylearn',
      path: '../../../../assets/images/easylearn.png'
    };
  }

  ngOnInit() {
    this.UserService.getUser().subscribe((res: any) => {
      this.firstButton = 'User profile';
      this.secondButton = 'Log out';
      this.link = '/profile';
      this.link2 = '/logout';
      this.isLogged = 1;
    },
      err => {
        this.firstButton = 'Sign up';
        this.secondButton = 'Sign in';
        this.link = '/register';
        this.link2 = '/login';
        this.isLogged = 0;
      });
      console.log(this.isLogged);
  }


  ngOnDestroy() {
    this.firstButton = '';
    this.secondButton = '';
  }

  ifLogged() {
    console.log(this.isLogged);
    return this.isLogged;
  }

  @HostListener('window:scroll', [])

  onWindowScroll() {
    const number = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    const x = document.getElementsByClassName('navbar')[0];
    x.classList.add('on-scroll');
    if (number === 0) {
      x.classList.remove('on-scroll');
    }
  }

}
