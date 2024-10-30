import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRoutineComponent } from './add-routine.component';

describe('AddRoutineComponent', () => {
  let component: AddRoutineComponent;
  let fixture: ComponentFixture<AddRoutineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddRoutineComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AddRoutineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
