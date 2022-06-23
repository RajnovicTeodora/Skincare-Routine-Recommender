import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuspiciousPatientsReportComponent } from './suspicious-patients-report.component';

describe('SuspiciousPatientsReportComponent', () => {
  let component: SuspiciousPatientsReportComponent;
  let fixture: ComponentFixture<SuspiciousPatientsReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuspiciousPatientsReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuspiciousPatientsReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
