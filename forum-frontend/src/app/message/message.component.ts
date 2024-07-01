import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from '../shared/http.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  id!: number;

  constructor(private route: ActivatedRoute, private httpService: HttpService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{this.id=params['id'];
    this.httpService.getMessages(this.id);
  });
    
  }

}
