import { Component, OnInit } from '@angular/core';
import { HttpService } from '../shared/http.service';

@Component({
  selector: 'app-rule',
  templateUrl: './rule.component.html',
  styleUrls: ['./rule.component.css']
})
export class RuleComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.httpService.getRules();
  }

}
