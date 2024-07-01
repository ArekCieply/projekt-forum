import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Message } from '../../shared/message.model';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {
  messages!: Message[];
  subscription!: Subscription;
  @Input() conversationId!: number
  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    this.messages = this.messageService.getMessages();
    this.subscription=this.messageService.messagesChanged.subscribe(
      (messages: Message[]) =>{
        this.messages=messages
      }
    )
  }

}
