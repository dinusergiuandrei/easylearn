import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service'
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent {
  constructor(private auth: AuthService, private router: Router, private cookieService: CookieService) { }

  ngOnInit() {
    this.auth.logout().subscribe((result: any) => {
      location.reload();
      this.cookieService.deleteAll();
      this.router.navigate(['']);
    },
      err => {
        this.router.navigate(['']);
      });
  }
}