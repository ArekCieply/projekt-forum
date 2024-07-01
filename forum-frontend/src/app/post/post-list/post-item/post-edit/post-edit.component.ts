import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../../auth/auth.service';
import { HttpService } from '../../../../shared/http.service';
import { Post } from '../../../../shared/post.model';

@Component({
  selector: 'app-post-edit',
  templateUrl: './post-edit.component.html',
  styleUrls: ['./post-edit.component.css']
})
export class PostEditComponent implements OnInit {
  @Input() post!: Post;
  @Output() displayEvent = new EventEmitter<boolean>();
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
  }

  save(content: string){
    this.post.content=content;
    this.post.userId=this.authService.getCurrentUser().id!;
    this.httpService.editPost(this.post);
    this.displayEvent.emit(false);
  }
}
