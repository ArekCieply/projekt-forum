import { Section } from "./section.model";
import { User } from "./user.model";

export class Sub_Section{
    constructor(
        public name: string,
        public userId: number,
        public sectionId: number,
        public id?: number,
        public user?: User,
        public section?: Section
    ){}
}