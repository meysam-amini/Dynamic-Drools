import {Component, OnDestroy, OnInit} from '@angular/core';
import {Rule} from '../rule';
import {RuleService} from '../rule.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-rules-list',
  templateUrl: './rules-list.component.html',
  styleUrls: ['./rules-list.component.css']
})
export class RulesListComponent implements OnInit,OnDestroy {
    rules: Rule[];
    mySubscription: any;

  constructor( private route: ActivatedRoute, private ruleService: RuleService,
                 private router: Router) {
    }

    ngOnInit() {
        this.ruleService.findAll().subscribe(data => {
            this.rules = data;
        });
    }

    editRule(rule: Rule) { // this.ruleService.edit(Rule).subscribe();
        this.ruleService.setEditingRule(rule);
        }

    deleteRule(rule: Rule) {
        this.ruleService.delete(rule).subscribe();
        this.reloadCurrentRoute();
    }

  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }
}
