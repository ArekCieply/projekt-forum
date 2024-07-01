import { Component, Input, OnInit } from '@angular/core';
import { Conversation } from '../../../shared/conversation.model';

@Component({
  selector: 'app-conversation-item',
  templateUrl: './conversation-item.component.html',
  styleUrls: ['./conversation-item.component.css']
})
export class ConversationItemComponent implements OnInit {

  @Input() conversation!: Conversation;
  @Input() index!: number;
  constructor() { }

  ngOnInit(): void {
  }

}
