import { Topic } from "./topic.model";
import { User } from "./user.model";

export class Post{
    constructor(
        public content: string,
        public userId: number,
        public topicId: number,
        public id?: number,
        public user?: User,
        public topic?: Topic,
        public score?: number
    ){}
}