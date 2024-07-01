import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Post } from '../../../shared/post.model';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.css']
})
export class PostCreateComponent implements OnInit {
  @Input() topicId!: number;
 // post!: Post;

  constructor(private httpService: HttpService, private authService: AuthService) { }

  submit(content: string) {
    
    var post = new Post(content, this.authService.getCurrentUser().id!, this.topicId)
    this.httpService.addPost(post);
    this.httpService.getPosts(this.topicId);
  }
  ngOnInit(): void {
  }

}
