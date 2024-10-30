import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Apollo, gql } from 'apollo-angular';
import { CommonModule } from '@angular/common';
import { tap } from 'rxjs';

@Component({
  selector: 'an-edit-room',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edit-room.component.html',
  styleUrl: './edit-room.component.css',
})
export class EditRoomComponent implements OnInit {
  @Input() roomId!: string;
  @Output() roomUpdated = new EventEmitter<void>();
  roomForm: FormGroup;

  constructor(private fb: FormBuilder, private apollo: Apollo) {
    this.roomForm = this.fb.group({
      name: [''],
    });
  }

  ngOnInit(): void {
    this.loadRoomDetails();
  }

  loadRoomDetails(): void {


    this.apollo
      .watchQuery<any>({
        query: GET_ROOM_BY_ID,
        variables: { id: this.roomId },
      })
      .valueChanges.pipe(
        tap(({ data }) => {
          const room = data.roomById;
          this.roomForm.patchValue({
            name: room.name,
          });
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error loading room details:', error),
      });
  }

  updateRoom(): void {
    const formData = this.roomForm.value;

    this.apollo
      .mutate({
        mutation: UPDATE_ROOM,
        variables: {
          id: this.roomId,
          name: formData.name,
        },
      })
      .pipe(
        tap(() => {
          this.roomUpdated.emit();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error updating room:', error),
      });

 }
}


const GET_ROOM_BY_ID = gql`
  query GetRoomById($id: ID!) {
    roomById(id: $id) {
      id
      name
    }
  }
`;

const UPDATE_ROOM = gql`
  mutation UpdateRoom($id: ID!, $name: String!) {
    updateRoom(id: $id, name: $name) {
      id
      name
    }
  }
`;
