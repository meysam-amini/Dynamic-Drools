import { Component, OnInit } from '@angular/core';
import {Rule} from '../rule';
import {ActivatedRoute, Router} from '@angular/router';
import {RuleService} from '../rule.service';

@Component({
  selector: 'app-rule-form',
  templateUrl: './rule-form.component.html',
  styleUrls: ['./rule-form.component.css']
})

export class RuleFormComponent implements OnInit {

    rule: Rule;
    isEdit: boolean = false;

    constructor(private route: ActivatedRoute, private router: Router, private ruleService: RuleService) {
        this.rule = new Rule();
    }

    onSubmit() {
        if (this.isEdit === false) {
        this.ruleService.save(this.rule).subscribe(result => this.gotoRuleList());
        } else {
            this.ruleService.edit(this.rule).subscribe(result => this.gotoRuleList());
        }
    }

    gotoRuleList() {
        this.router.navigate(['/rules']);
    }

    ngOnInit(): void {
        this .route.paramMap.subscribe(parametermap => {
           const txt = parametermap.get('txt');
           if (txt.localeCompare('add') === 0) {
               this.rule = new Rule();
               this.isEdit = false;
           } else {
            this.rule = this.ruleService.getEditingRule() ;
            this.isEdit = true;
           }
        });

        }

}
