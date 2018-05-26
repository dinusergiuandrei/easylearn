import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../services/user-account.service';
import { UserModel } from '../../../shared';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
    
  public users: Array<UserModel>;
  public userid;

  constructor(private route: ActivatedRoute, private UserService:UserService, private router:Router) { }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.userid = params.get('id');
  
    });
    this.UserService.getUser(this.userid).subscribe((res: any) => {
      this.users = res;
      console.log(this.users);
    },
    err => {
      console.log(err);
    })}



 

}
