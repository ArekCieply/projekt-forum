import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Rule } from '../../shared/rule.model';
import { RuleService } from '../rule.service';

@Component({
  selector: 'app-rule-list',
  templateUrl: './rule-list.component.html',
  styleUrls: ['./rule-list.component.css']
})
export class RuleListComponent implements OnInit {
  rules!: Rule[];
  subscription!: Subscription;
  constructor(private ruleService: RuleService) { }

  ngOnInit(): void {
    this.rules=this.ruleService.getRules();
    this.subscription=this.ruleService.rulesChanged.subscribe(
      (rules: Rule[]) =>{
        this.rules=rules
      }
    )
  }

}
