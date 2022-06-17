import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutineProductsWithReactionComponent } from './routine-products-with-reaction.component';

describe('RoutineProductsWithReactionComponent', () => {
  let component: RoutineProductsWithReactionComponent;
  let fixture: ComponentFixture<RoutineProductsWithReactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoutineProductsWithReactionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutineProductsWithReactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
