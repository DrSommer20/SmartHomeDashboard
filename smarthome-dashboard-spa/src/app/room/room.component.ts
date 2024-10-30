import {
  Component,
  OnInit,
  OnDestroy,
  ViewChild,
  ViewContainerRef,
  ComponentRef,
} from '@angular/core';
import { Apollo, QueryRef } from 'apollo-angular';
import { Subscription } from 'rxjs';
import { tap } from 'rxjs/operators';
import { CommonModule } from '@angular/common';
import { gql } from 'apollo-angular';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'an-room',
  standalone: true,
  imports: [CommonModule, ModalComponent],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css',
})
export class RoomComponent implements OnInit, OnDestroy {
  rooms: any[] = [];
  private querySubscription!: Subscription;
  private roomsQuery!: QueryRef<any>;
  @ViewChild('modalContainer', { read: ViewContainerRef })
  modalContainer!: ViewContainerRef;
  private modalRef!: ComponentRef<ModalComponent>;

  constructor(private apollo: Apollo) {}

  ngOnInit(): void {
    this.loadRooms();
  }

  ngOnDestroy(): void {
    if (this.querySubscription) {
      this.querySubscription.unsubscribe();
    }
  }

  loadRooms(): void {
    this.roomsQuery = this.apollo.watchQuery<any>({
      query: GET_ALL_ROOMS,
      pollInterval: 20000,
    });

    this.querySubscription = this.roomsQuery.valueChanges
      .pipe(
        tap(({ data }) => {
          this.rooms = data.allRooms || [];
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error loading rooms:', error),
      });
  }

  deleteRoom(roomId: string): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'ConfirmDelete';

    this.modalRef.instance.onConfirm.subscribe(() => {
      this.modalRef.destroy();
      this.apollo
        .mutate({
          mutation: DELETE_ROOM,
          variables: {
            id: roomId,
          },
        })
        .pipe(
          tap(() => {
            this.roomsQuery.refetch();
          })
        )
        .subscribe({
          next: () => {},
          error: (error) => console.error('Error deleting room:', error),
        });
    });
    this.modalRef.instance.onCancel.subscribe(() => {
      this.modalRef.destroy();
    });
  }

  editRoom(roomId: string): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'EditRoom';
    this.modalRef.instance.data = { roomId };
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.roomsQuery.refetch();
    });
  }

  switchRoom(roomId: string, state: boolean): void {
    this.apollo
      .mutate({
        mutation: SWITCH_ROOM,
        variables: {
          id: roomId,
          state: state,
        },
      })
      .pipe(
        tap(() => {
          this.roomsQuery.refetch();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error switching room:', error),
      });
    }

}

const GET_ALL_ROOMS = gql`
  query GetAllRooms {
    allRooms {
      id
      name
    }
  }
`;

const DELETE_ROOM = gql`
  mutation DeleteRoom($id: ID!) {
    deleteRoom(id: $id)
  }
`;

const SWITCH_ROOM = gql`
  mutation SwitchRoom($id: ID!, $state: Boolean!) {
    switchRoom(id: $id, state: $state)
  }
`;
