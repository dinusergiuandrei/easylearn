import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingScreenService } from '../../../../services/loading-screen.service';
import { AuthService } from '../../../../services/auth.service';

import { FormControl } from '@angular/forms';

// import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user: FormGroup;
  public responseText = '';

  question = new FormControl('', [Validators.required]);

  constructor(
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
    // private notification: NotificationService
  ) {}


  register() {
    this.auth.register(this.user.value).subscribe(
      res => {
        this.router.navigate(['/login']);
      },
      err => {
        this.responseText = 'Something went wrong. Please check your credentials and refresh the page.';
      }
    );
  }

  ngOnInit() {

    this.user = this.formBuilder.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      email: [
        '',
        Validators.compose([
          Validators.required,
          Validators.email
        ])
      ],
      access: 'student',
      password: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(32)
        ])
      ],
      repassword: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(32)
        ])
      ],
      question: [
        Validators.compose([
          Validators.required
        ]
        )
      ],

      answer: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
        ]
        )
      ]
    });
  }

}
