import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Topic } from '../../shared/topic.model';
import { TopicService } from '../../topic/topic.service';

@Component({
  selector: 'app-profile-activity-list',
  templateUrl: './profile-activity-list.component.html',
  styleUrls: ['./profile-activity-list.component.css']
})
export class ProfileActivityListComponent implements OnInit {
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
