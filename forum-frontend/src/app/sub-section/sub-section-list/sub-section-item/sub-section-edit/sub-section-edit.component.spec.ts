import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubSectionEditComponent } from './sub-section-edit.component';

describe('SubSectionEditComponent', () => {
  let component: SubSectionEditComponent;
  let fixture: ComponentFixture<SubSectionEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubSectionEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubSectionEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
