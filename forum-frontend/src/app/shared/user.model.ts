export class User{
    constructor(
        public name: string,
        public score?: number,
        public role?: string,
        public isBanned?: boolean,
        public id?: number,
        public token?: string,

    ){}
}