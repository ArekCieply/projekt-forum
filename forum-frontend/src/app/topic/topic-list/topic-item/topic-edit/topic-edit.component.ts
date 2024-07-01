import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../../auth/auth.service';
import { HttpService } from '../../../../shared/http.service';
import { Topic } from '../../../../shared/topic.model';

@Component({
  selector: 'app-topic-edit',
  templateUrl: './topic-edit.component.html',
  styleUrls: ['./topic-edit.component.css']
})
export class TopicEditComponent implements OnInit {
  @Input() topic!: Topic;
  @Output() displayEvent = new EventEmitter<boolean>();
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
  }

  save(name: string, content: string){
    this.topic.name=name;
    this.topic.userId=this.authService.getCurrentUser().id!;
    this.httpService.editTopic(this.topic);
    this.displayEvent.emit(false);
  }
}
