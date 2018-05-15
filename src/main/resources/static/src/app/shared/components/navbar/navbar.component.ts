import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  logoPath: string;

  constructor() {
    this.logoPath = '../../../../assets/images/easylearn.png';
  }

  ngOnInit() {}

}
