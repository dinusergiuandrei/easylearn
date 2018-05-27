import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user-account.service';
import { UserModel } from '../../../shared';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public user: UserModel;
  public firstName: string;
  public lastName: string;
  public eMail: string;
  public newpass: string;
  public renewpass: string;
  public responseText: string;

  constructor(private route: ActivatedRoute, private UserService: UserService, private router: Router) { }

  ngOnInit() {
    this.UserService.getUser().subscribe((res: any) => {
      this.user = res;
    },
      err => {
        this.router.navigate(['/error404'])
      })
  }

  public editClick(): void {
    if (this.firstName != null) {
      this.user.firstName = this.firstName;
    }
    if (this.lastName != null) {
      this.user.name = this.lastName;
    }
    if (this.eMail != null) {
      this.user.email = this.eMail;
    }

    if (this.newpass != this.renewpass && this.newpass !=null)
      this.responseText = 'The passwords are not the same!';
      console.log(this.newpass+' '+this.renewpass)
    else {
      this.responseText = 'Succes!';
      this.user.password = this.newpass;
    }

    this.UserService.updateUser(this.user).subscribe((res: any) => {
    },
      err => {
        console.log(err);
      })
    this.router.navigate(['/profile']);
  }


  public deleteClick(): void {
    this.UserService.deleteUser().subscribe((res: any) => {
      err => {
        console.log(err);
      }
    })
  }
}
