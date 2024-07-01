import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Post } from '../../../shared/post.model';
import { User } from '../../../shared/user.model';

@Component({
  selector: 'app-post-item',
  templateUrl: './post-item.component.html',
  styleUrls: ['./post-item.component.css']
})
export class PostItemComponent implements OnInit {
  @Input() post!: Post;
  @Input() index!: number;
  @Input() topicId!: number;
  logedUser!: User;
  display = false;
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
    this.logedUser = this.authService.getCurrentUser();
  }

  deletePost() {
    this.post.topicId=this.topicId;
    this.post.userId=this.authService.getCurrentUser().id!;
    this.httpService.deletePost(this.post)
  }

  editPost() {
    this.display = !this.display;
  }

  close(i: boolean) {
    this.display = false;
  }

  plus(){
    this.httpService.plusPost(this.post.id!, this.logedUser.id!, this.topicId);
  }

  minus(){
    this.httpService.minusPost(this.post.id!, this.logedUser.id!, this.topicId);
  }
}
