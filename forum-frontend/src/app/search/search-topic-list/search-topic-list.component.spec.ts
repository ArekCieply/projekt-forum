import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTopicListComponent } from './search-topic-list.component';

describe('SearchTopicListComponent', () => {
  let component: SearchTopicListComponent;
  let fixture: ComponentFixture<SearchTopicListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchTopicListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchTopicListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
