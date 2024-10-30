import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditRoutineComponent } from './edit-routine.component';

describe('EditRoutineComponent', () => {
  let component: EditRoutineComponent;
  let fixture: ComponentFixture<EditRoutineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditRoutineComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EditRoutineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
