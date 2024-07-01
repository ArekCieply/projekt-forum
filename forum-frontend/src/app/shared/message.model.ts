import { Conversation } from "./conversation.model";
import { User } from "./user.model";

export class Message {
    constructor(
        public content: string,
        public userId: number,
        public privateConversationId: number,
        public id?: number,
        public user?: User,
        public privateConversation?: Conversation,
    ){}
}
