import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AllDevicesComponent } from './device/device.component';
import { HomeComponent } from './home/home.component';
import { RoomComponent } from './room/room.component';
import { RoutineComponent } from './routine/routine.component';
import { UserProfileComponent } from './user-profile/user-profile.component'; 
import { AuthGuard } from './auth.guard';
import { AuthComponent } from './auth/auth.component';
import { SignupComponent } from './signup/signup.component';

export const routes: Routes = [
    // Protected routes
    {
      path: 'home', component: HomeComponent,
      children: [
        { path: 'dashboard', component: DashboardComponent ,  data: { title: 'Dashboard' }},
        { path: 'devices', component: AllDevicesComponent,  data: { title: 'Devices', button: 'Add Device'}},
        { path: 'rooms', component: RoomComponent,  data: { title: 'Rooms', button: 'Add Room'}},
        { path: 'routines', component: RoutineComponent,  data: { title: 'Routines', button: 'Add Routine'}},
        { path: 'user', component: UserProfileComponent ,  data: { title: 'User Profile' }}
      ], //canActivate: [AuthGuard]
    },
  {
    path: 'auth', component: AuthComponent,
    children: [
      { path: 'login', component: LoginComponent, data: { animation: 'loginPage' } },
      { path: 'sign-up', component: SignupComponent, data: { animation: 'signupPage' } }
    ]
  },


  { path: '**', redirectTo: '/auth/login'}
];

