
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'an-user-profile',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  @Output() titleEvent = new EventEmitter<string>();
  userForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder, private router: Router) {
    this.userForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      password: [''],
      pat: ['']
    });
  }

  ngOnInit(): void {
    this.getUserData();
    this.titleEvent.emit('User Profile');
  }

  getUserData(): void {

    this.http.get<any>('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user').subscribe(
      response => {
        console.log('Erfolgreiche Antwort:', response);
        this.userForm.patchValue({
          firstName: response.firstName,
          lastName: response.lastName,
          email: response.email,
          pat: response.pat
        });
      },
      error => {
        console.error('Fehler bei der Anfrage:', error);
      }
    );
  }

  updateField(field: string, newValue: string): void {
    const data = {
      "new-value": newValue,
      "field": field
    };

    this.http.put('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user', data).subscribe(
      response => {
        console.log(`Feld ${field} erfolgreich aktualisiert:`, response);
        if (field === 'password') {
          console.log(`Feld ${field} wird aktualisiert.`);
        } else {
          console.log(`Feld ${field} wird aktualisiert auf: ${newValue}`);
        }
      },
      error => {
        console.error(`Fehler beim Aktualisieren von ${field}:`, error);
      }
    );
  }

  onEditButtonClick(): void {
    const currentData = this.userForm.value;

    this.http.get<any>('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user').subscribe(
      response => {
        console.log('Aktuelle Daten vom Server:', response);

        if (response.firstName !== currentData.firstName) {
          this.updateField('firstName', currentData.firstName);
        }
        if (response.lastName !== currentData.lastName) {
          this.updateField('lastName', currentData.lastName);
        }
        if (response.email !== currentData.email) {
          this.updateField('email', currentData.email);
        }
        if (currentData.password !== '') {
          this.updateField('password', currentData.password);
        }
        if (response.pat !== currentData.pat) {
          this.updateField('pat', currentData.pat);
        }
      },
      error => {
        console.error('Fehler beim Abrufen der aktuellen Daten:', error);
      }
    );
  }

  deleteProfile(): void {
    const confirmation = confirm('Are you sure you want to delete your profile? This action cannot be undone.');
    if (confirmation) {
      this.http.delete('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/user').subscribe(
        response => {
          console.log('Profile deleted successfully:', response);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Error deleting profile:', error);
        }
      );
    }
  }
  
}
