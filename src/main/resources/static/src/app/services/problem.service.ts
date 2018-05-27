import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { ProblemModel } from '../shared';

@Injectable({
  providedIn: 'root'
})
export class ProblemService {

  constructor(private http: HttpClient) {}
  private api = `${environment.api}`;

  public getProblems(categoryId: number): Observable<any> {
    return this.http.get(this.api + '/problems/category/' + categoryId).map((result: Array<ProblemModel>) => {
      return result;
    });
  }

  public getProblem(problemId: number): Observable<any> {
    return this.http.get(this.api + '/problems/' + problemId).map((result: ProblemModel) => {
      return result;
    })
  }

}
