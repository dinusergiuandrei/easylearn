import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
    
  public user;

  constructor() { }

  ngOnInit() {
    this.user ={
        facebook: 'John.Doe',
        linkedin: 'John.Doe',
        github: 'John.Doe',
        name: 'John Doe',
        email: 'johndoe@easylearn.ro',
        phone: '0235 / 192.192'
        };
  }

}
