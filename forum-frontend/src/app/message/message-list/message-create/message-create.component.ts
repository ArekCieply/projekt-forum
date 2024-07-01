import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Message } from '../../../shared/message.model';

@Component({
  selector: 'app-message-create',
  templateUrl: './message-create.component.html',
  styleUrls: ['./message-create.component.css']
})
export class MessageCreateComponent implements OnInit {
  @Input() conversationId!: number
  constructor(private httpService: HttpService, private authService: AuthService) { }

  submit(content: string) {
    var message = new Message(content, this.authService.getCurrentUser().id!, this.conversationId)
    this.httpService.addMessage(message);
    this.httpService.getMessages(this.conversationId);
  }

  ngOnInit(): void {
  }

}
