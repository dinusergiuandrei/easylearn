import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {}
  private api = `${environment.api}/user`;
  
  login(user): Observable<any> {
    return this.http.post(`${environment.api}/login`, {
      email: user.email,
      password: user.password
    });
  }

  register(user): Observable<any> {
    return this.http.post(`${environment.api}/users`, {
      id:0,
      name: user.firstname,
      firstName: user.lastname,
      email: user.email,
      password: user.password,
      secretPassword: '',
      secretAnswer: '',
      score: 0,
    });
  }

  // setSession(response) {
  //   this.redirect = response.data.UserPermission.permission;
  //   this._cookie.set('logged', 'true');
  //   this._cookie.set('session', response.token);
  //   this._cookie.set('user', response.username);
  //   this._cookie.set('all_user', JSON.stringify(response));
  // }

  // destroySession() {
  //   this._cookie.delete('logged');
  //   this._cookie.delete('session');
  //   this._cookie.delete('user');
  //   this._cookie.delete('all_user');
  // }
}