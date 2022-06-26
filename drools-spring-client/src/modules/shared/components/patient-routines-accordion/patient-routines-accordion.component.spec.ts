import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientRoutinesAccordionComponent } from './patient-routines-accordion.component';

describe('PatientRoutinesAccordionComponent', () => {
  let component: PatientRoutinesAccordionComponent;
  let fixture: ComponentFixture<PatientRoutinesAccordionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PatientRoutinesAccordionComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientRoutinesAccordionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
