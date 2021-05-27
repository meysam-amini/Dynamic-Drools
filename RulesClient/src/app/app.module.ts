import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RulesListComponent } from './rules-list/rules-list.component';
import { RuleFormComponent } from './rule-form/rule-form.component';
import { HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RuleService} from './rule.service';
import {AppRoutingModule} from './app-routing.module';
import {JsonService} from './json.service';

@NgModule({
  declarations: [
    AppComponent,
    RulesListComponent,
    RuleFormComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule
     , HttpClientModule , FormsModule
  ],
  providers: [RuleService, JsonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
