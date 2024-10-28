import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { boxResizeAnimation } from '../auth/boxResizeAnimation';

@Component({
  selector: 'an-signup',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent implements OnInit {
  signUpForm: FormGroup;
  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.signUpForm = this.fb.group({}); // Initialize sign up form
  }

  ngOnInit() {
    this.signUpForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      password: [''],
      pat: [''],
    });
  }

  signUpSuccess = false;
  signUpFailure = false;

  onSignUpSubmit() {
    //TODO: write GraphQL mutation to sign up user
    const signUpUrl =
      'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/auth/sign-up';

    const signUpData = this.signUpForm.value;

    this.http
      .post(signUpUrl, signUpData, {
        headers: { 'Content-Type': 'application/json' },
      })
      .subscribe({
        next: (response) => {
          this.signUpSuccess = true;
          this.signUpFailure = false;
        },
        error: (error) => {
          console.error('Sign up error:', error);
          this.signUpSuccess = false;
          this.signUpFailure = true;
        },
      });
  }
}
