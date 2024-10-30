import {
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
  ViewChild,
  ViewContainerRef,
  ComponentRef,
} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  ReactiveFormsModule,
} from '@angular/forms';
import { Apollo, gql, QueryRef } from 'apollo-angular';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { tap } from 'rxjs';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'an-routine',
  standalone: true,
  imports: [CommonModule, ModalComponent],
  templateUrl: './routine.component.html',
  styleUrl: './routine.component.css',
})
export class RoutineComponent implements OnInit, OnDestroy {
  routines: any[] = [];
  refreshInterval: any;
  routinesQuery!: QueryRef<any>;
  @ViewChild('modalContainer', { read: ViewContainerRef })
  modalContainer!: ViewContainerRef;
  private modalRef!: ComponentRef<ModalComponent>;
  private querySubscription!: Subscription;

  constructor(private apollo: Apollo) {}

  ngOnInit(): void {
    this.routinesQuery = this.apollo.watchQuery<any>({
      query: GET_ALL_ROUTINES,
      pollInterval: 20000,
    });
    this.querySubscription = this.routinesQuery.valueChanges
      .pipe(
        tap(({ data }) => {
          this.routines = data.allRoutines || [];
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error loading routines:', error),
      });
  }

  ngOnDestroy(): void {
    this.querySubscription.unsubscribe();
  }

  toggleRoutine(event: any, routineId: string): void {
    const isChecked = event.target.checked;
    const state = isChecked ? 'on' : 'off';

    this.apollo
      .mutate({
        mutation: SWITCH_ROUTINE,
        variables: {
          id: routineId,
          state: state === 'on',
        },
      })
      .pipe(
        tap(() => {
          this.routinesQuery.refetch();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error updating routine:', error),
      });
  }

  deleteRoutine(routineId: string): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'ConfirmDelete';

    this.modalRef.instance.onConfirm.subscribe(() => {
    this.modalRef.destroy();
      this.apollo
        .mutate({
          mutation: DELETE_ROUTINE,
          variables: {
            id: routineId,
          },
        })
        .pipe(
          tap(() => {
            this.routinesQuery.refetch();
          })
        )
        .subscribe({
          next: () => {},
          error: (error) => console.error('Error deleting routine:', error),
        });
    });
    this.modalRef.instance.onCancel.subscribe(() => {
      // Simply destroy the modal if cancelled
      this.modalRef.destroy();
    });
  }

  editRoutine(routineId: string): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'EditRoutine';
    this.modalRef.instance.data = { routineId };
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.routinesQuery.refetch();
    });
  }
}

const GET_ALL_ROUTINES = gql`
  query GetAllRoutines {
    allRoutines {
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

const DELETE_ROUTINE = gql`
  mutation DeleteRoutine($id: ID!) {
    deleteRoutine(id: $id)
  }
`;

const SWITCH_ROUTINE = gql`
  mutation SwitchRoutine($id: ID!, $state: Boolean!) {
    switchRoutine(id: $id, state: $state)
  }
`;
