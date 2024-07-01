import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../../auth/auth.service';
import { HttpService } from '../../../../shared/http.service';
import { Rule } from '../../../../shared/rule.model';

@Component({
  selector: 'app-rule-edit',
  templateUrl: './rule-edit.component.html',
  styleUrls: ['./rule-edit.component.css']
})
export class RuleEditComponent implements OnInit {
  @Input() rule!: Rule;
  @Output() displayEvent = new EventEmitter<boolean>();
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
  }

  save(content: string){
    this.rule.rule=content;
    this.rule.userId=this.authService.getCurrentUser().id!;
    this.httpService.editRule(this.rule);
    this.displayEvent.emit(false);
  }
}
