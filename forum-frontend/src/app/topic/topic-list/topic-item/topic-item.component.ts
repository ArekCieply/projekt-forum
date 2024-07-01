import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Topic } from '../../../shared/topic.model';
import { User } from '../../../shared/user.model';

@Component({
  selector: 'app-topic-item',
  templateUrl: './topic-item.component.html',
  styleUrls: ['./topic-item.component.css']
})
export class TopicItemComponent implements OnInit {
  @Input() topic!: Topic;
  @Input() index!: number;
  @Input() subSectionId!: number;
  logedUser!: User;
  display = false;
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
    this.logedUser = this.authService.getCurrentUser();
  }


  deleteTopic() {
    this.topic.subSectionId=this.subSectionId;
    this.topic.userId=this.authService.getCurrentUser().id!;
    this.httpService.deleteTopic(this.topic)
  }
  editTopic() {
    this.display = !this.display;
  }

  close(i: boolean) {
    this.display = false;
  }
}
