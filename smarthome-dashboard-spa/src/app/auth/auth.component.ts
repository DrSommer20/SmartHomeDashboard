import { Component, ChangeDetectorRef } from '@angular/core';
import {
  ActivatedRoute,
  RouterModule,
  RouterOutlet,
  RouterLink,
  Router,
} from '@angular/router';
import { boxResizeAnimation } from './boxResizeAnimation';
import { trigger, transition, style, animate } from '@angular/animations';
import { SignupComponent } from '../signup/signup.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'an-auth',
  standalone: true,
  imports: [CommonModule, RouterModule, RouterOutlet, RouterLink],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css',
  animations: [
    boxResizeAnimation,
    trigger('routeAnimation', [
      transition('* <=> *', [
        style({ opacity: 0, transform: 'translateY(-20px)' }),
        animate(
          '0.5s ease-in-out',
          style({ opacity: 1, transform: 'translateY(0)' })
        ),
      ]),
    ]),
  ],
})
export class AuthComponent {
  isSignUpPage = false;

  constructor(
    public route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  onRouteChange(event: any): void {
    this.isSignUpPage = event instanceof SignupComponent;
    this.cdr.detectChanges(); // Manually trigger change detection
  }

  prepareRoute(outlet: RouterOutlet) {
    return (
      outlet &&
      outlet.activatedRouteData &&
      outlet.activatedRouteData['animation']
    );
  }
}
