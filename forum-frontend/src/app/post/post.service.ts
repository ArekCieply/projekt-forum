import { Subject } from "rxjs";
import { Post } from "../shared/post.model";

export class PostService {
  private posts: Post[] = [];
  postsChanged = new Subject<Post[]>();
  getPosts() {
    return this.posts;
  }
  getPost(index: number) {
    return this.posts[index];
  }
  setPosts(Post: Post[]) {
    this.posts = Post;
    this.postsChanged.next(this.posts);
  }
}