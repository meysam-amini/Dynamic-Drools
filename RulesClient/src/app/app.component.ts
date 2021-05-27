import {AfterContentInit, AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {RuleService} from './rule.service';
import {Json} from './json';
import {JsonService} from './json.service';
import {NavigationEnd, Route, Router} from '@angular/router';
import {f} from '../assets/myjs.js';



@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

    title: string;
    json: Json;
    text: string;

    constructor(private ruleService: RuleService, private jsonService: JsonService,private router: Router) {
        this.title = 'Add Ur Rules, Set Json, And Press Apply Button';
    }

    onApply() {
        this.applyRules();

    }

    applyRules(){
      this.ruleService.applyRules().subscribe( data=>{
        if(data === 1) {
          this.applyJson();
        }
        location.reload();
      });
    }

    applyJson(){
      this.jsonService.saveJson(this.json).subscribe();
    }


  ngOnInit(): void {
    this.jsonService.getEditingJson().subscribe(data => {
      this.json = data;
    },error1=> this.text = error1);
  }


}
