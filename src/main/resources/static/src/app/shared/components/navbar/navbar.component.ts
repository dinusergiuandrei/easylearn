import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  logo: { name: string; path: string; };

  constructor() {
    this.logo = {
      name: 'easylearn',
      path: '../../../../assets/images/easylearn.png'
    };
  }
  
  ngOnInit() {}
  @HostListener("window:scroll", [])
  onWindowScroll() {
    const number = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    var x = document.getElementsByClassName('navbar')[0];
    x.classList.add("on-scroll");
    if(number==0) x.classList.remove("on-scroll");
  }
}
