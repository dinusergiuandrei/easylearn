import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    // private notification: NotificationService
  ) {}


  register() {
    // this.auth.register(this.user.value).subscribe(
    //   res => {
    //     this.notification.push({
    //       message: 'You registered successfully',
    //       type: 'success'
    //     });
    //     this.router.navigate(['/login']);
    //   },
    //   err => {
    //     this.notification.push({
    //       message: 'Registration failed. Please check your details',
    //       type: 'error'
    //     });
    //   }
    // );
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
      ]
    });
  }

}
