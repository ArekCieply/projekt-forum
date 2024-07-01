import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTopicItemComponent } from './search-topic-item.component';

describe('SearchTopicItemComponent', () => {
  let component: SearchTopicItemComponent;
  let fixture: ComponentFixture<SearchTopicItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchTopicItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchTopicItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
