import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileActivityItemComponent } from './profile-activity-item.component';

describe('ProfileActivityItemComponent', () => {
  let component: ProfileActivityItemComponent;
  let fixture: ComponentFixture<ProfileActivityItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileActivityItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileActivityItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
