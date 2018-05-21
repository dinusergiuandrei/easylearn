import { Component, OnInit } from '@angular/core';
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

}
