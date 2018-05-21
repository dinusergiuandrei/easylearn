import { Component, OnInit } from '@angular/core';
import { User } from '../user-details';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
    
  user: User ={
  facebook: 'John.Doe',
  linkedin: 'John.Doe',
  github: 'John.Doe',
  name: 'John Doe',
  email: 'johndoe@easylearn.ro',
  phone: '0235 / 192.192'
  };

  constructor() { }

  ngOnInit() {
  }

}
