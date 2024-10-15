import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'an-room',
  standalone: true,
  imports: [],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {
  @Output() titleEvent = new EventEmitter<string>();

  ngOnInit(): void {
    this.titleEvent.emit('Roomss');
  }
}
