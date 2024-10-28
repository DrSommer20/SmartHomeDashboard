// src/app/edit-routine/edit-routine.component.ts
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Apollo, gql } from 'apollo-angular';
import { CommonModule } from '@angular/common';
import { tap } from 'rxjs';

@Component({
  selector: 'an-edit-routine',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edit-routine.component.html',
  styleUrl: './edit-routine.component.css',
})
export class EditRoutineComponent implements OnInit {
  @Input() routineId!: string;
  @Output() routineUpdated = new EventEmitter<void>();
  routineForm: FormGroup;

  constructor(private fb: FormBuilder, private apollo: Apollo) {
    this.routineForm = this.fb.group({
      routineName: [''],
      routineTime: [''],
      actions: this.fb.array([]),
    });
  }

  ngOnInit(): void {
    this.loadRoutineDetails();
  }

  loadRoutineDetails(): void {
    this.apollo
      .watchQuery<any>({
        query: GET_ROUTINE_BY_ID,
        variables: { id: this.routineId },
      })
      .valueChanges.pipe(
        tap(({ data }) => {
          const routine = data.routineById;
          this.routineForm.patchValue({
            routineName: routine.name,
            routineTime: routine.triggerTime,
            actions: routine.actions,
          });
        })
      )
      .subscribe({
        next: () => {},
        error: (error) =>
          console.error('Error loading routine details:', error),
      });
  }

  updateRoutine(): void {
    const formData = this.routineForm.value;

    this.apollo
      .mutate({
        mutation: UPDATE_ROUTINE,
        variables: {
          id: this.routineId,
          name: formData.routineName,
          triggerTime: formData.routineTime,
          actions: formData.actions,
        },
      })
      .pipe(
        tap(() => {
          this.routineUpdated.emit();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error updating routine:', error),
      });
  }
}

const UPDATE_ROUTINE = gql`
  mutation UpdateRoutine(
    $id: ID!
    $name: String
    $triggerTime: String
    $actions: [ActionInput]
  ) {
    updateRoutine(
      id: $id
      name: $name
      triggerTime: $triggerTime
      actions: $actions
    ) {
      id
    }
  }
`;

const GET_ROUTINE_BY_ID = gql`
  query GetRoutineById($id: ID!) {
    routineById(id: $id) {
      id
      name
      triggerTime
      actions {
        deviceID
        deviceName
        setTo
      }
      state
    }
  }
`;
