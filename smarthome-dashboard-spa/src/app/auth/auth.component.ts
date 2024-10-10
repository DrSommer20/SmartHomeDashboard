import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet, RouterLink, ChildrenOutletContexts } from '@angular/router';
import { boxResizeAnimation } from './boxResizeAnimation';
import { SignupComponent } from '../signup/signup.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'an-auth',
  standalone: true,
  imports: [CommonModule, RouterModule, RouterOutlet, RouterLink],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css',
  animations: [
    boxResizeAnimation
  ]
})
export class AuthComponent {
  isSignUpPage = false;
  constructor(public route: ActivatedRoute) { }

  onRouteChange(event: any): void {
    this.isSignUpPage = event instanceof SignupComponent;
  }
}
