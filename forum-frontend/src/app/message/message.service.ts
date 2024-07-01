import { Subject } from "rxjs";
import { Message } from "../shared/message.model";

export class MessageService {
  private messages: Message[] = [];
  messagesChanged = new Subject<Message[]>();
  getMessages() {
    return this.messages;
  }
  getMessage(index: number) {
    return this.messages[index];
  }
  setMessages(Message: Message[]) {
    this.messages = Message;
    this.messagesChanged.next(this.messages);
  }
  addMessage(Message: Message) {
    this.messages.push(Message);
  }
}
