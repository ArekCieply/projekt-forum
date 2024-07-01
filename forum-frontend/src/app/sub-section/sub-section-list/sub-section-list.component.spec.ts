import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubSectionListComponent } from './sub-section-list.component';

describe('SubSectionListComponent', () => {
  let component: SubSectionListComponent;
  let fixture: ComponentFixture<SubSectionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubSectionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubSectionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
