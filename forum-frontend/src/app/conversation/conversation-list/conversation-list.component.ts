import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Conversation } from '../../shared/conversation.model';
import { ConversationService } from '../conversation.service';

@Component({
  selector: 'app-conversation-list',
  templateUrl: './conversation-list.component.html',
  styleUrls: ['./conversation-list.component.css']
})
export class ConversationListComponent implements OnInit {
  conversations!: Conversation[];
  subscription!: Subscription;
  constructor(private conversationService: ConversationService) { }

  ngOnInit(): void {
    this.conversations = this.conversationService.getConversations();
    this.subscription = this.conversationService.conversationsChanged.subscribe(
      (conversations: Conversation[]) => {
        this.conversations = conversations
      }
    )
  }


}
