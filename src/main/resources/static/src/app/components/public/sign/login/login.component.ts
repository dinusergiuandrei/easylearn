import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoadingScreenService } from '../../../../services/loading-screen.service';
import { AuthService } from '../../../../services/auth.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: FormGroup;
  public responseText = '';

  constructor(
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  login() {
    this.auth.login(this.user.value).subscribe(
      res => {
        location.reload();
        this.router.navigate(['/profile']);
        this.responseText = 'Succes!';
      },
      err => {
        this.responseText = 'Wrong username and/or password.';
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
