import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { ProblemModel } from '../shared';
import { RunModel } from '../shared/models/run-model';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) {}
  private api = `${environment.api}`;

  public getTests(problemId: number): Observable<any> {
    return this.http.get(this.api + '/problems/'+ problemId + '/tests').map((result: Array<RunModel>) => {
      return result;
    });
  }
}
