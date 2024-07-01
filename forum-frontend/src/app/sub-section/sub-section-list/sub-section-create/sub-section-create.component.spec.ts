import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubSectionCreateComponent } from './sub-section-create.component';

describe('SubSectionCreateComponent', () => {
  let component: SubSectionCreateComponent;
  let fixture: ComponentFixture<SubSectionCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubSectionCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubSectionCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
