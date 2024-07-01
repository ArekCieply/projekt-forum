import { Subject } from "rxjs";
import { Conversation } from "../shared/conversation.model";

export class ConversationService {
  private conversations: Conversation[] = [];
  conversationsChanged = new Subject<Conversation[]>();
  getConversations() {
    return this.conversations;
  }
  getConversation(index: number) {
    return this.conversations[index];
  }
  setConversations(Conversation: Conversation[]) {
    this.conversations = Conversation;
    this.conversationsChanged.next(this.conversations);
  }
  addConversation(Conversation: Conversation) {
    this.conversations.push(Conversation);
  }
}
