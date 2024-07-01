import { Component, OnInit } from '@angular/core';
import { Topic } from '../../shared/topic.model';
import { SearchService } from '../../search/search.service'
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-search-topic-list',
  templateUrl: './search-topic-list.component.html',
  styleUrls: ['./search-topic-list.component.css']
})
export class SearchTopicListComponent implements OnInit {
  topics!: Topic[];
  subscription!: Subscription;
  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
    this.topics=this.searchService.getTopics();
    this.subscription=this.searchService.topicsChanged.subscribe(
      (topics: Topic[]) =>{
        this.topics=topics
      }
    )
  }

}
