import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { UserModel } from '../shared';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}
  private api = `${environment.api}`;

  public getUser(userId: number): Observable<any> {
    return this.http.get(this.api + '/users/'+ userId).map((result: Array<UserModel>) => {
      return result;
    });
  }

 
    
  }


