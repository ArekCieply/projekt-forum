import { Component, Input, OnInit } from '@angular/core';
import { Topic } from '../../../shared/topic.model';

@Component({
  selector: 'app-search-topic-item',
  templateUrl: './search-topic-item.component.html',
  styleUrls: ['./search-topic-item.component.css']
})
export class SearchTopicItemComponent implements OnInit {
  @Input() topic!: Topic;
  @Input() index!: number;
  constructor() { }

  ngOnInit(): void {
  }

}
