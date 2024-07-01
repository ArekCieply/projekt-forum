import { User } from "./user.model";

export class Conversation {
    constructor(
        public user1Id: number,
        public user2Id: number,
        public id?: number,
        public user1?: User,
        public user2?: User
    ){}
}
