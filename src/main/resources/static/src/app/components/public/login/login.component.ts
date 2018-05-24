import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoadingScreenService } from '../../../services/loading-screen.service';
import { AuthService } from '../../../services/auth.service';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: FormGroup;

  constructor(
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
    private notification: NotificationService
  ) { }

  login() {
    this.auth.login(this.user.value).subscribe(
      res => {
        this.notification.push({
          message: 'You logged in successfully',
          type: 'success'
        });
        res.username = this.user.value.username;
        this.auth.setSession(res);
        this.router.navigate([
          `/${res.data.UserPermission.permission}/profile`
        ]);
      },
      err => {
        const message = err.error.response;
        this.notification.push({
          message: 'Login Failed! Check again your email or password.',
          type: 'error'
        });
      }
    );
  }

  ngOnInit() {
    this.user = this.formBuilder.group({
      email: [
        '',
        Validators.compose([
          Validators.required,
          Validators.email
        ])
      ],
      password: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(32)
        ])
      ]
    });
  }

}
