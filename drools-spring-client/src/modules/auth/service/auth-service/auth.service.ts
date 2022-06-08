import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/modules/shared/services/api-service/api.service';
import { UserService } from 'src/modules/shared/services/user-service/user.service';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private apiService: ApiService,
    private router: Router,
    private userService: UserService
  ) {}

  login(user: any): Observable<any> {
    const loginHeaders = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true,
      observe: 'response' as 'response',
    };

    const body = {
      username: user.username,
      password: user.password,
    };
    return this.http
      .post(
        `${environment.baseUrl}/${environment.login}`,
        JSON.stringify(body),
        loginHeaders
      )
      .pipe(
        map((res: any) => {
          if (res.body.acessToken === null) return;

          // Set JWT token
          console.log('Login success');

          sessionStorage.setItem('jwt', res.body.accessToken);

          // Set user role
          let jwtData = res.body.accessToken.split('.')[1];
          let decodedJwtJsonData = window.atob(jwtData);
          let decodedJwtData = JSON.parse(decodedJwtJsonData);
          let role = decodedJwtData.role;

          sessionStorage.setItem('role', role);
        })
      );
  }

  /*signup(user: any) {
    const signupHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.signup_url, JSON.stringify(user), signupHeaders)
      .pipe(map(() => {
        console.log('Sign up success');
      }));
  }*/

  logout() {
    //this.router.navigate(['skincare/login']);
  }

  tokenIsPresent() {
    const token = JSON.stringify(sessionStorage.getItem('jwt'));
    return token != undefined && token != null;
  }

  // Get user role
  getRole() {
    const role = JSON.stringify(sessionStorage.getItem('role'));
    return role.substring(1, role.length - 1);
  }

  getToken() {
    console.log('getToken');
    console.log(JSON.stringify(sessionStorage.getItem('jwt')));
    return JSON.stringify(sessionStorage.getItem('jwt'));
  }
}
