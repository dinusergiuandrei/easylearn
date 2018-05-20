import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.scss']
})
export class SignComponent implements OnInit, AfterViewInit {

  @ViewChild('login') login;
  @ViewChild('register') register;

  currentPath: string;
  showReg: boolean;

  constructor(private router: Router) { }

  switchComp(sw) {
    this.showReg = sw;
    if (sw) {
      this.login.nativeElement.classList.remove('active');
      this.register.nativeElement.classList.add('active');
    } else {
      this.login.nativeElement.classList.add('active');
      this.register.nativeElement.classList.remove('active');
    }
  }

  ngOnInit() {
    this.currentPath = this.router.url;
    this.showReg = false;
  }

  ngAfterViewInit() {
    if (this.login) {
      this.login.nativeElement.classList.add('active');
    }
  }

}
