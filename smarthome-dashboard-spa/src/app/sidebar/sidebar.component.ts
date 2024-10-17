import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { RouterLink, Router} from '@angular/router';
import { LoginService } from '../login/login.service';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'an-sidebar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit{
  firstName = '';
  lastName = '';
  initials = '';  

  @Output() hoverChange = new EventEmitter<string>();

  constructor(private router: Router, private loginService: LoginService, private http:HttpClient) { }

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

  getUserData(){
    this.http.get<any>('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user').subscribe(
      response => {
        this.firstName = response.firstName;
        this.lastName = response.lastName;
        this.initials = response.firstName.charAt(0) + response.lastName.charAt(0);
      },
      error => {
        console.error('Fehler bei der Anfrage:', error);
      }
    );
  }
}
