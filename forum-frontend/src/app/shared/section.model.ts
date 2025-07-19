import { User } from "./user.model";

export class Section{
    constructor(
        public name: string,
        public userId: number,
        public id?: number,
        public user?: User
    ){}
}