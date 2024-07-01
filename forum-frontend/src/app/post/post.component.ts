import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { tap } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { HttpService } from '../shared/http.service';
import { Topic } from '../shared/topic.model';
import { User } from '../shared/user.model';
import { TopicService } from '../topic/topic.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  id!: number;
  topic: Topic=new Topic("","",0,0,0,0,new User(""))
  logedUser!: User;
  url!: string;
  constructor(private http: HttpClient, private route: ActivatedRoute, 
    private httpService: HttpService, private authService: AuthService) { }

  ngOnInit(): void {
    this.logedUser = this.authService.getCurrentUser();
    this.route.params.subscribe(params => { this.id = params['id'] });
    this.httpService.getPosts(this.id);
    this.getTopic(this.id);
    this.url=window.location.href;
    
  }

  getTopic(topicId: number) {
    const params = new HttpParams().append('topicId', topicId);
    this.http.get<Topic>('http://localhost:8080/topic/one', { params }).pipe(
      tap((topic) => {
        this.topic = topic
        console.log(this.topic)
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }

  plus(){
    this.httpService.plusTopic(this.topic.id!, this.logedUser.id!);
    this.getTopic(this.id);
  }

  minus(){
    this.httpService.minusTopic(this.topic.id!, this.logedUser.id!);
    this.getTopic(this.id);
  }

}
