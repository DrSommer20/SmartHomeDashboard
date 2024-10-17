import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { RoutineService } from './routine.service'
import { CommonModule } from '@angular/common';

@Component({
  selector: 'an-routine',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './routine.component.html',
  styleUrl: './routine.component.css'
})
export class RoutineComponent implements OnInit, OnDestroy {
  @Output() titleEvent = new EventEmitter<string>();
  routines: any[] = [];
  refreshInterval: any;
  constructor(private routineService: RoutineService) { }

  ngOnInit(): void {
    this.titleEvent.emit('Routines');
    this.updateRoutines();
    this.refreshInterval = setInterval(() => this.updateRoutines(), 30000);
  }

  ngOnDestroy(): void {
    clearInterval(this.refreshInterval);
  }

  updateRoutines(): void {
    this.routineService.getRoutines().subscribe(
      response => {
        this.routines = response.routines || [];
      },
      error => {
        console.error('Fehler bei der Anfrage:', error);
      }
    );
  }

  toggleRoutine(event: any, routineId: string): void {
    const isChecked = event.target.checked;
    const state = isChecked ? 'on' : 'off';
    this.routineService.switchRoutine(routineId, state).subscribe(
      response => {
        this.updateRoutines();
      },
      error => {
        console.error('Error updating routine:', error);
        // Handle error response
      }
    );
  }

  refreshContent(): void {
    this.updateRoutines();
  }

  
  deleteRoutine(routineId: string): void {
    this.routineService.deleteRoutine(routineId).subscribe(
      response => {
        this.updateRoutines();
      },
      error => {
        console.error('Error deleting routine:', error);
      }
    );
  }

  editRoutine(routineId: string): void {
    // Implement edit routine
  } 

}
