import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../../../shared';
import { UserService } from '../../../services/user-account.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  logo: { name: string; path: string; };
  logged = '';
  firstButton = '';
  secondButton = '';

  // tslint:disable-next-line:no-shadowed-variable
  constructor(private UserService: UserService) {
    this.logo = {
      name: 'easylearn',
      path: '../../../../assets/images/easylearn.png'
    };
  }

  ngOnInit() {
    this.UserService.getUser().subscribe((res: any) => {
      this.firstButton = 'User profile';
      this.secondButton = 'Log out';
    },
      err => {
        this.firstButton = 'Sign up';
        this.secondButton = 'Sign in';
      });
  }

  // tslint:disable-next-line:use-life-cycle-interface
  ngOnDestroy() {
    this.firstButton = '';
    this.secondButton = '';
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
