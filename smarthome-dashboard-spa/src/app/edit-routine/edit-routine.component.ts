import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  ReactiveFormsModule,
} from '@angular/forms';
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
  devices: any[] = [];

  constructor(private fb: FormBuilder, private apollo: Apollo) {
    this.routineForm = this.fb.group({
      routineName: [''],
      routineTime: [''],
      actions: this.fb.array([]),
    });
  }

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.apollo
      .watchQuery<any>({
        query: GET_INITIAL_DATA,
        variables: { routineId: this.routineId },
      })
      .valueChanges.pipe(
        tap(({ data }) => {
          this.devices = data.allDevices || [];
          const routine = data.routineById;
          this.routineForm.patchValue({
            routineName: routine.name,
            routineTime: routine.triggerTime,
          });
          this.setActions(routine.actions);
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error loading initial data:', error),
      });
  }

  setActions(actions: any[]): void {
    const actionFGs = actions.map((action) =>
      this.fb.group({
        deviceID: [action.deviceID],
        setTo: [action.setTo],
      })
    );
    const actionFormArray = this.fb.array(actionFGs);
    this.routineForm.setControl('actions', actionFormArray);
  }

  get actions(): FormArray {
    return this.routineForm.get('actions') as FormArray;
  }

  addAction(): void {
    this.actions.push(
      this.fb.group({
        deviceID: [''],
        setTo: [''],
      })
    );
  }

  removeAction(index: number): void {
    this.actions.removeAt(index);
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
  mutation updateRoutine(
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

const GET_INITIAL_DATA = gql`
  query GetInitialData($routineId: ID!) {
    routineById(id: $routineId) {
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
    allDevices {
      id
      name
    }
  }
`;
