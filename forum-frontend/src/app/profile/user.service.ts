import { Subject } from 'rxjs';
import { User } from '../shared/user.model';

export class UserService {

  private users: User=User;
  userChanged = new Subject<User>();

  getUser() {
    return this.users;
  }
  
  setUser(User: User) {
    this.users = User;
    this.userChanged.next(this.users);
  }
}
