import { Subject } from "rxjs";
import { Rule } from "../shared/rule.model";

export class RuleService {
  private rules: Rule[] = [];
  rulesChanged = new Subject<Rule[]>();
  
  getRules() {
    return this.rules;
  }
  getRule(index: number) {
    return this.rules[index];
  }
  setRules(Rule: Rule[]) {
    this.rules = Rule;
    this.rulesChanged.next(this.rules);
  }
}
