import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Json} from './json';
import {Observable, of} from 'rxjs';


@Injectable()
export class JsonService {

  private jsonUrl: string;



  constructor(private http: HttpClient) {
    this.jsonUrl = 'http://localhost:7070/json';
  }

  public saveJson(json: Json) {
    return this.http.post<string>(this.jsonUrl, json.content);
  }

  public getEditingJson(): Observable<Json> {
    return this.http.get<Json>(this.jsonUrl);
  }


}
