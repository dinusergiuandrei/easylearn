import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class ProblemService {

  constructor(private http: HttpClient) {}
  private api = `${environment.api}/problems/category/`;

  request(id): Observable<any> {
    return this.http.get(`${environment.api}/problems/category/`+ id);
  }
}
