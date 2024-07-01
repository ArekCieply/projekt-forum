import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Rule } from '../../../shared/rule.model';

@Component({
  selector: 'app-rule-create',
  templateUrl: './rule-create.component.html',
  styleUrls: ['./rule-create.component.css']
})
export class RuleCreateComponent implements OnInit {

  constructor(private httpService: HttpService, private authService: AuthService) { }

  ngOnInit(): void {
  }
  submit(content: string) {
    
    var rule = new Rule(content, this.authService.getCurrentUser().id!)
    this.httpService.addRule(rule);
    this.httpService.getRules();
  }

}
