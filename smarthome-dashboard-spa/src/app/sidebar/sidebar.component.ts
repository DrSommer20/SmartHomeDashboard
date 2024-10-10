import { Component, Output, EventEmitter } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'an-sidebar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  firstName = 'Tim';
  lastName = 'Sommer';
  initials = 'TS';

  @Output() hoverChange = new EventEmitter<string>();

  onMouseEnter() {
    this.hoverChange.emit('265px');
  }

  onMouseLeave() {
    this.hoverChange.emit('90px');
  }
}
