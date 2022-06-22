import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileWithRoutinesComponent } from './profile-with-routines.component';

describe('ProfileWithRoutinesComponent', () => {
  let component: ProfileWithRoutinesComponent;
  let fixture: ComponentFixture<ProfileWithRoutinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileWithRoutinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileWithRoutinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
