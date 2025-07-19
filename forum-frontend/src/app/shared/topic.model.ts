import { Sub_Section } from "./sub-section.model";
import { User } from "./user.model";

export class Topic{
    constructor(
        public name: string,
        public content: string,
        public userId: number,
        public subSectionId: number,
        public score?: number,
        public id?: number,
        public user?: User,
        public subSection?: Sub_Section
    ){}
}