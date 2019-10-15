export class User {
    id: string;
    username: string;
    password: string;
    role: Role;
  }

 export enum Role {
      MEMBER,
      ADMIN
  }  