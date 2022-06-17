import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientRoutinesTableComponent } from './patient-routines-table.component';

describe('PatientRoutinesTableComponent', () => {
  let component: PatientRoutinesTableComponent;
  let fixture: ComponentFixture<PatientRoutinesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientRoutinesTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientRoutinesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
