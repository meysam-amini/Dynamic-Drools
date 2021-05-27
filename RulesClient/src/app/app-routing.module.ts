import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RulesListComponent} from './rules-list/rules-list.component';
import {RuleFormComponent} from './rule-form/rule-form.component';

const routes: Routes = [
    { path: 'rules', component: RulesListComponent },
    {path: '', component: RulesListComponent},
    { path: 'editrule/:txt', component: RuleFormComponent,
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
