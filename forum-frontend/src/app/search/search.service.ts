import { Observable, of, Subject } from 'rxjs';
import { Topic } from '../shared/topic.model';
import { User } from '../shared/user.model';


export class SearchService {

  private topics: Topic[] = [];
  topicsChanged = new Subject<Topic[]>();

  getTopics() {
    return this.topics;
  }
  getTopicsO(): Observable<Topic[]> {
    return of(this.topics);
  }
  getTopic(index: number) {
    return this.topics[index];
  }
  setTopics(topic: Topic[]) {
    this.topics = topic;
    this.topicsChanged.next(this.topics);
  }

  private users: User[] = [];
  usersChanged = new Subject<User[]>();
  getUsers() {
    return this.users;
  }
  getUser(index: number) {
    return this.users[index];
  }
  setUsers(User: User[]) {
    this.users = User;
    this.usersChanged.next(this.users)
  }
}
