import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/Observable/of';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {}
  private api = `${environment.api}/user`;

  login(user): Observable<any> {
    return this.http.get(
                `${environment.api}/login?email=` + user.email + '&password=' + user.password,
               { observe: 'response', withCredentials: true }
             )
      .map((res: any) => {
          console.log(res);
          console.log(res.headers.get('Set-Cookie'));
          return res;
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
}
