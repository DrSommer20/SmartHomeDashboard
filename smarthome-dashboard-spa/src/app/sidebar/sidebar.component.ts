import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { Apollo, gql } from 'apollo-angular';
import { LoginService } from '../login/login.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'an-sidebar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent implements OnInit {
  firstName = '';
  lastName = '';
  initials = '';

  @Output() hoverChange = new EventEmitter<string>();

  constructor(
    private router: Router,
    private loginService: LoginService,
    private apollo: Apollo
  ) {}

  ngOnInit(): void {
    this.getUserData();
  }

  onMouseEnter() {
    this.hoverChange.emit('265px');
  }

  onMouseLeave() {
    this.hoverChange.emit('90px');
  }

  navigateToUser() {
    this.router.navigate(['/home/user']);
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/auth/login']);
  }

  getUserData() {
    this.apollo
      .watchQuery<any>({
        query: GET_USER_INFO,
      })
      .valueChanges.subscribe(
        ({ data }) => {
          const user = data.userInfo;
          this.firstName = user.firstName;
          this.lastName = user.lastName;
          this.initials = user.firstName.charAt(0) + user.lastName.charAt(0);
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
  }
}

const GET_USER_INFO = gql`
  query GetUserInfo {
    userInfo {
      id
      firstName
      lastName
      email
      pat
      isVerified
    }
  }
`;
