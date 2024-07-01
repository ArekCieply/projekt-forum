import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Topic } from '../../shared/topic.model';
import { TopicService } from '../topic.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  @Input() subSectionId!: number;
  topics! : Topic[];
  subscription!: Subscription;
  constructor(private topicService: TopicService) { }

  ngOnInit(): void {
    this.topics=this.topicService.getTopics();
    this.subscription=this.topicService.topicsChanged.subscribe(
      (topics: Topic[]) =>{
        this.topics=topics
      }
    )
  }

}
