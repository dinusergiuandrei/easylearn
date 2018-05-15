import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoadingScreenService } from '../../../services/loading-screen.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: FormGroup;
  loading = false;
  constructor(
    private formBuilder: FormBuilder,
    private screen: LoadingScreenService,
    private router: Router,
  ) {
    this.user = this.formBuilder.group({
      username: [
        '',
        [Validators.required, Validators.email]
      ],
      password: [
        '',
        [Validators.required, Validators.minLength(6), Validators.maxLength(32)]
      ]
    });
  }

  ngOnInit() {
  }

}
