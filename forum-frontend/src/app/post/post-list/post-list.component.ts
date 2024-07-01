import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Post } from '../../shared/post.model';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  posts! : Post[];
  @Input() topicId!: number;
  subscription!: Subscription;
  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.posts=this.postService.getPosts();
    this.subscription=this.postService.postsChanged.subscribe(
      (posts: Post[]) =>{
        this.posts=posts
      }
    )
  }

}
