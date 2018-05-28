import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { RunModel } from '../shared';

@Injectable({
  providedIn: 'root'
})
export class RunService {

  private api = `${environment.api}`;

  constructor(private http: HttpClient) { }

  public getRunsBySubmission(submissionId: number): Observable<any> {
  console.log(this.api + '/submissions/' + submissionId +  '/runs');
    return this.http.get(this.api + '/submissions/' + submissionId +  '/runs');
  }

}
