import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/modules/shared/services/api-service/api.service';
import { UserService } from 'src/modules/shared/services/user-service/user.service';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoggedInUser } from 'src/modules/shared/models/logged-in-user';

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

          let username = decodedJwtData.username;
          sessionStorage.setItem('username', username);
        })
      );
  }

  logout() {
    return this.apiService.get(`${environment.baseUrl}/${environment.logout}`);
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
  // Get user username
  getUsername() {
    const username = JSON.stringify(sessionStorage.getItem('username'));
    return username.substring(1, username.length - 1);
  }

  getLoggedInUser() {
    const role = this.getRole();
    const username = this.getUsername();
    return new LoggedInUser(username, role);
  }

  getToken() {
    return JSON.stringify(sessionStorage.getItem('jwt'));
  }
}
