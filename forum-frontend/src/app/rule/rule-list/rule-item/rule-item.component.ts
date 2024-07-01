import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Rule } from '../../../shared/rule.model';
import { User } from '../../../shared/user.model';

@Component({
  selector: 'app-rule-item',
  templateUrl: './rule-item.component.html',
  styleUrls: ['./rule-item.component.css']
})
export class RuleItemComponent implements OnInit {
  @Input() rule!: Rule;
  @Input() index!: number;
  logedUser!: User;
  display = false;
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
    if(this.authService.getCurrentUser()!==null){
      this.logedUser = this.authService.getCurrentUser();
      }
  }

  deleteRule() {
    this.rule.userId=this.authService.getCurrentUser().id!;
    this.httpService.deleteRule(this.rule)
  }
  editRule() {
    this.display = !this.display;
  }

  close(i: boolean) {
    this.display = false;
  }

}
