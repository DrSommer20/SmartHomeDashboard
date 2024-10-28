import { Component, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Apollo, gql } from 'apollo-angular';
import { tap } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'an-add-room',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-room.component.html',
  styleUrl: './add-room.component.css',
})
export class AddRoomComponent {
  @Output() roomAdded = new EventEmitter<void>();
  roomForm: FormGroup;

  constructor(private fb: FormBuilder, private apollo: Apollo) {
    this.roomForm = this.fb.group({
      name: [''],
    });
  }

  addRoom(): void {
    const formData = this.roomForm.value;

    this.apollo
      .mutate({
        mutation: CREATE_ROOM,
        variables: {
          name: formData.name,
        },
      })
      .pipe(
        tap(() => {
          this.roomAdded.emit();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error adding room:', error),
      });
  }
}

const CREATE_ROOM = gql`
  mutation CreateRoom($name: String!) {
    createRoom(name: $name)
  }
`;
