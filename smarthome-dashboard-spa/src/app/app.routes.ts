import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AllDevicesComponent } from './all-devices/all-devices.component';
import { HomeComponent } from './home/home.component';
import { RoomComponent } from './room/room.component';
import { RoutineComponent } from './routine/routine.component';
import { UserProfileComponent } from './user-profile/user-profile.component'; 
import { AuthGuard } from './auth.guard'; // Import AuthGuard
import { AuthComponent } from './auth/auth.component';
import { SignupComponent } from './signup/signup.component';

export const routes: Routes = [
  {
    path: 'auth', component: AuthComponent,
    children: [
      { path: 'login', component: LoginComponent, data: { animation: 'loginPage' } },
      { path: 'sign-up', component: SignupComponent, data: { animation: 'signupPage' } }
    ]
  },
  // Protected routes
  {
    path: 'home', component: HomeComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'all-devices', component: AllDevicesComponent},
      { path: 'rooms', component: RoomComponent},
      { path: 'routines', component: RoutineComponent},
      { path: 'user-profile', component: UserProfileComponent }
    ], canActivate: [AuthGuard]
  },

  { path: '**', redirectTo: '/auth/login'}
];

