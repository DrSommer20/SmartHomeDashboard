import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { tap } from 'rxjs';
@Component({
  selector: 'an-room',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {
  rooms: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadRooms();
  }

  loadRooms(): void {
    this.http.get<any>('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room')
      .pipe(
        tap(response => {
          this.rooms = response.rooms;
        })
      )
      .subscribe({
        next: () => {},
        error: error => console.error('Error loading rooms:', error)
      });
  }

  deleteRoom(roomId: string): void {
    this.http.delete(`https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/room/${roomId}`)
      .pipe(
        tap(() => {
          this.loadRooms(); // Refresh the list of rooms
        })
      )
      .subscribe({
        next: () => {},
        error: error => console.error('Error deleting room:', error)
      });
  }

  refreshContent(): void {
    this.loadRooms();
  }


}
