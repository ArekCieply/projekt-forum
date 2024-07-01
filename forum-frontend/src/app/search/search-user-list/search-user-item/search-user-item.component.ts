import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../../shared/user.model';

@Component({
  selector: 'app-search-user-item',
  templateUrl: './search-user-item.component.html',
  styleUrls: ['./search-user-item.component.css']
})
export class SearchUserItemComponent implements OnInit {
  @Input() user!: User;
  @Input() index!: number;
  constructor() { }

  ngOnInit(): void {
  }

}
