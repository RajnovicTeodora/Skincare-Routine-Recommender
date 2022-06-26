import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientRoutinesComponent } from './patient-routines.component';

describe('PatientRoutinesComponent', () => {
  let component: PatientRoutinesComponent;
  let fixture: ComponentFixture<PatientRoutinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientRoutinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientRoutinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
