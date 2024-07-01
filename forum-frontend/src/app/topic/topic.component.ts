import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from '../shared/http.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {
  id!: number;
  constructor(private route: ActivatedRoute, private httpService: HttpService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{this.id=params['id']})
    this.httpService.getTopics(this.id)
  }

}
