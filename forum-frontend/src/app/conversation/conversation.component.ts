import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from '../shared/http.service';
import { AuthService } from '../auth/auth.service'

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {
  id!: number;
  display = false;
  constructor(private route: ActivatedRoute, private httpService: HttpService, private authService: AuthService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{this.id=params['id']});
    //this.id!=this.authService.getCurrentUser().id;
    console.log(this.authService.getCurrentUser())
    this.httpService.getConversations(this.id);
  }

  toggle(){
    this.display=!this.display;
  }

}
