import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Topic } from '../../../shared/topic.model';

@Component({
  selector: 'app-topic-create',
  templateUrl: './topic-create.component.html',
  styleUrls: ['./topic-create.component.css']
})
export class TopicCreateComponent implements OnInit {
  @Input() subSectionId!: number;
  //private topic!: Topic;
  
  constructor(private httpService: HttpService, private authService: AuthService ) { }

  submit(title: string, content: string) {
    var topic= new Topic(title,content,this.authService.getCurrentUser().id!, this.subSectionId);
    this.httpService.addTopic(topic);
  }

  ngOnInit(): void {
  }

}
