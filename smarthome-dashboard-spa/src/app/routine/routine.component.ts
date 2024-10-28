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
  @Output() titleEvent = new EventEmitter<string>();
  routines: any[] = [];
  refreshInterval: any;
  getsQuery!: QueryRef<any>;
  @ViewChild('modalContainer', { read: ViewContainerRef })
  modalContainer!: ViewContainerRef;
  private modalRef!: ComponentRef<ModalComponent>;
  private querySubscription!: Subscription;

  constructor(private apollo: Apollo) {}

  ngOnInit(): void {
    this.getsQuery = this.apollo.watchQuery<any>({
      query: GET_ALL_ROUTINES,
      pollInterval: 20000,
    });
    this.querySubscription = this.getsQuery.valueChanges.subscribe(
      ({ data, loading }) => {
        this.routines = data.allRoutines || [];
      }
    );
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
          this.getsQuery.refetch();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error updating routine:', error),
      });
  }

  deleteRoutine(routineId: string): void {
    this.apollo
      .mutate({
        mutation: DELETE_ROUTINE,
        variables: {
          id: routineId,
        },
      })
      .pipe(
        tap(() => {
          this.getsQuery.refetch();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error deleting routine:', error),
      });
  }

  editRoutine(routineId: string): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'EditRoutine';
    this.modalRef.instance.data = { routineId };
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.getsQuery.refetch();
    });
  }

  refreshContent(): void {
    this.getsQuery.refetch();
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
