import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from '../../shared/user.model';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-search-user-list',
  templateUrl: './search-user-list.component.html',
  styleUrls: ['./search-user-list.component.css']
})
export class SearchUserListComponent implements OnInit {
  users! : User[];
  subscription!: Subscription;
  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
    this.users=this.searchService.getUsers();
    this.subscription=this.searchService.usersChanged.subscribe(
      (users: User[]) =>{
        this.users=users
      }
    )
  }

}
