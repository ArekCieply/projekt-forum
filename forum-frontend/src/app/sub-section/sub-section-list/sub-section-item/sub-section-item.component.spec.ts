import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubSectionItemComponent } from './sub-section-item.component';

describe('SubSectionItemComponent', () => {
  let component: SubSectionItemComponent;
  let fixture: ComponentFixture<SubSectionItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubSectionItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubSectionItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
