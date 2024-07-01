import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Conversation } from '../shared/conversation.model';
import { HttpService } from '../shared/http.service';
import { User } from '../shared/user.model';
import { UserService } from './user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  id!: number;
  convId!: Conversation;
  conversation!: Conversation;
  user!: User;
  logedUser!: User;
  subscription!: Subscription;
  display = false;
  constructor(private route: ActivatedRoute, private httpService: HttpService,
    private authService: AuthService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => { this.id = params['id'] })
    this.httpService.getUser(this.id);
    this.httpService.getUserActivity(this.id);
    this.user = this.userService.getUser();
    this.subscription=this.userService.userChanged.subscribe(
      (user: User) =>{
        this.user=user
      }
    )
    this.logedUser=this.authService.getCurrentUser();
    console.log(this.logedUser)
  }

  sendMessage() {
    this.conversation = new Conversation(this.authService.getCurrentUser().id!, this.id)
    this.httpService.addConversation(this.conversation);
    this.convId=this.httpService.getConvId();
    this.subscription=this.httpService.convId.subscribe(
      (conv: Conversation) =>{
        this.convId=conv
        this.router.navigate(['conversation', this.convId.id ])
      }
    )
    console.log(this.convId)
    
  }
  changePass(){
    this.display = !this.display;
  }
  close(i: boolean) {
    this.display = false;
  }
  ban(){
    this.httpService.ban(this.authService.getCurrentUser().id!, this.user.id!);
  }
  unBan(){
    this.httpService.unBan(this.authService.getCurrentUser().id!, this.user.id!);
  }

  promote(){
    this.httpService.promote(this.authService.getCurrentUser().id!, this.user.id!);
  }

  demote(){
    this.httpService.demote(this.authService.getCurrentUser().id!, this.user.id!);
  }

}
