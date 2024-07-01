import { Subject } from "rxjs";
import { Topic } from "../shared/topic.model";

export class TopicService {

  private topics: Topic[] = [];
  topicsChanged = new Subject<Topic[]>();
  getTopics() {
    return this.topics;
  }
  getTopic(index: number) {
    return this.topics[index];
  }
  setTopics(topic: Topic[]) {
    this.topics = topic;
    this.topicsChanged.next(this.topics);
  }
}
