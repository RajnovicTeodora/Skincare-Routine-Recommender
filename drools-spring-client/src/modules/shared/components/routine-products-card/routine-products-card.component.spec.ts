import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutineProductsCardComponent } from './routine-products-card.component';

describe('RoutineProductsCardComponent', () => {
  let component: RoutineProductsCardComponent;
  let fixture: ComponentFixture<RoutineProductsCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoutineProductsCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutineProductsCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
