import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

@Component({
  selector: 'an-add-room',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-room.component.html',
  styleUrl: './add-room.component.css'
})
export class AddRoomComponent {
  @Output() roomAdded = new EventEmitter<void>();
  roomForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.roomForm = this.fb.group({
      name: ['']
    });
  }


  addRoom(): void {
    const formData = this.roomForm.value;

    const newRoom = {
      name: formData.name
    };

    this.http.post('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room', newRoom)
      .pipe(
        tap(() => {
          this.roomAdded.emit();
        })
      )
      .subscribe({
        next: () => {},
        error: error => console.error('Error adding room:', error)
      });
  }

}
