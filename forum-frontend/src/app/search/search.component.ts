import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { Topic } from '../shared/topic.model';
import { User } from '../shared/user.model';
import { SearchService } from './search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  choice!: string;
  constructor(private http: HttpClient, private searchService: SearchService) { }

  ngOnInit(): void {
  }

  submit(searchText: string) {
    if (this.choice == "użytkownikach") {
      this.findUsers(searchText);
    } else if (this.choice == "tematach") {
      this.findTopics(searchText);
    }
  }

  findTopics(name: string) {
    const params = new HttpParams().append('name', name);
    this.http.get<Topic[]>('http://localhost:8080/topic/find',{params}).pipe(
      tap((topics) => {
        this.searchService.setTopics(topics);
      })
    )
    .subscribe((response) => {
      console.log(response);
    });
  }

  findUsers(name: string) {
    const params = new HttpParams().append('name', name);
    this.http.get<User[]>('http://localhost:8080/users/find',{params}).pipe(
      tap((users) => {
        this.searchService.setUsers(users);
      })
    )
    .subscribe((response) => {
      console.log(response);
    });
  }
}
