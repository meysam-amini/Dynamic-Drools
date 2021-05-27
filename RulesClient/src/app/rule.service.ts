import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Rule} from './rule';

@Injectable()
export class RuleService {

    private rulesUrl: string;
    private applyRulesUrl: string;
    private deleteRulesUrl: string;
    private editRulesUrl: string;
    private editingRule: Rule;
    constructor(private http: HttpClient) {
        this.rulesUrl = 'http://localhost:7070/rules';
        this.applyRulesUrl = 'http://localhost:7070/applyrules';
        this.deleteRulesUrl = 'http://localhost:7070/deleterule';
        this.editRulesUrl = 'http://localhost:7070/editrule';
    }

    public findAll(): Observable<Rule[]> {
        return this.http.get<Rule[]>(this.rulesUrl);
    }

    public save(rule: Rule) {
        return this.http.post<Rule>(this.rulesUrl, rule);
    }
    public applyRules() {
        return this.http.post(this.applyRulesUrl, 1);
    }

    public delete(rule: Rule) {
        return this.http.post<Rule>(this.deleteRulesUrl, rule.id);
    }

    public edit(rule: Rule) {
        return this.http.post<Rule>(this.editRulesUrl, rule);
    }
    public setEditingRule(rule: Rule) {
        localStorage.setItem('editing_rule', JSON.stringify(rule));
    }
    public getEditingRule() {
        const data = localStorage.getItem('editing_rule');
        return JSON.parse(data);
    }

}
