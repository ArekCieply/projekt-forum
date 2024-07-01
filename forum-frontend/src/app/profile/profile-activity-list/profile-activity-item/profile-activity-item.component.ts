import { Component, Input, OnInit } from '@angular/core';
import { Post } from '../../../shared/post.model';
import { Topic } from '../../../shared/topic.model';

@Component({
  selector: 'app-profile-activity-item',
  templateUrl: './profile-activity-item.component.html',
  styleUrls: ['./profile-activity-item.component.css']
})
export class ProfileActivityItemComponent implements OnInit {
  @Input() topic!: Topic;
  @Input() index!: number;
  constructor() { }

  ngOnInit(): void {
  }

}
