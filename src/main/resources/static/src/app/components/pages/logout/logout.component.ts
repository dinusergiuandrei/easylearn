import { Component, OnInit } from '@angular/core';
import { AuthService} from '../../../services/auth.service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent {
  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit() {
    this.auth.logout().subscribe((result: any) => {
      console.log(result);
  },
      err => {
          console.log(err);
      });
    location.reload();
    this.router.navigate(['']);
  }
}