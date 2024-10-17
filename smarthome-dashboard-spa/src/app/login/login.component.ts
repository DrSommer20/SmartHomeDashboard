import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'an-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, public router: Router) {
    this.loginForm = this.fb.group({}); // Initialize login form
  }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [''],
      password: [''],
      keepSignedIn: [false] 
    });

    
  }

  onLoginSubmit() {
    const loginUrl = 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/auth';

    const loginData = {
      email: this.loginForm.get('email')?.value,
      password: this.loginForm.get('password')?.value
    };

    const keepSignedIn = this.loginForm.get('keepSignedIn')?.value;

    this.http.post(loginUrl, loginData, { headers: { 'Content-Type': 'application/json' } })
      .subscribe({
        next: (response: any) => {
          // Store token in localStorage if "Keep me Signed in" is checked, else in sessionStorage
          if (keepSignedIn) {
            localStorage.setItem('authToken', response.token);
          } else {
            sessionStorage.setItem('authToken', response.token);
          }
          this.router.navigate(['/home/dashboard']); // Redirect
        },
        error: (error) => {
          console.error('Login error:', error);
          this.errorMessage = 'The email or password is incorrect. Please try again.';
        }
      });
  }

  
}
