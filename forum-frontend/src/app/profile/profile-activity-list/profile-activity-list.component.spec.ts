import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileActivityListComponent } from './profile-activity-list.component';

describe('ProfileActivityListComponent', () => {
  let component: ProfileActivityListComponent;
  let fixture: ComponentFixture<ProfileActivityListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileActivityListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileActivityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
