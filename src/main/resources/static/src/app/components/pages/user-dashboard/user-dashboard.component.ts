import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  public user;
  chartOptions = {
    responsive: true,
    legend: {
      display: false
    }
  };
  chartData = [{ data: [17, 25, 3, 46] }];
  chartColors = [{
    backgroundColor: ['#4286f4', '#2c680b', '#680a30', '#d1ce27']
  }];
  chartLabels = ['C', 'C++', 'Python', 'Java'];

  constructor() { }

  ngOnInit() {
    this.user = {
      facebook: 'John.Doe',
      linkedin: 'John.Doe',
      github: 'John.Doe',
      name: 'John Doe',
      email: 'johndoe@easylearn.ro',
      phone: '0235 / 192.192'
    };
  }

}
