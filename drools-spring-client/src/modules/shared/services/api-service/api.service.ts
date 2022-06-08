import {
  HttpClient,
  HttpHeaders,
  HttpParams,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, filter, Observable } from 'rxjs';
export enum RequestMethod {
  Get = 'GET',
  Head = 'HEAD',
  Post = 'POST',
  Put = 'PUT',
  Delete = 'DELETE',
  Options = 'OPTIONS',
  Patch = 'PATCH',
}

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  headers = new HttpHeaders({
    Accept: 'application/json',
    'Content-Type': 'application/json',
    Authorization: `Bearer ${JSON.stringify(sessionStorage.getItem('jwt'))}`,
  });

  constructor(private http: HttpClient) {}

  get(path: string, args?: any): Observable<any> {
    const options = {
      headers: this.headers,
      params: {},
      withCredentials: true,
      observe: 'response' as 'response',
    };

    if (args) {
      options.params = this.serialize(args);
    }

    return this.http
      .get(path, options)
      .pipe(catchError(this.checkError.bind(this)));
  }

  post(path: string, body: any, customHeaders?: HttpHeaders): Observable<any> {
    const options = {
      headers: this.headers,
      params: {},
      withCredentials: true,
      observe: 'response' as 'response',
    };
    return this.http.post(path, body, options);
  }

  put(path: string, body: any): Observable<any> {
    const options = {
      headers: this.headers,
      params: {},
      withCredentials: true,
      observe: 'response' as 'response',
    };
    return this.http.put(path, body, options);
  }

  delete(path: string, body?: any): Observable<any> {
    return this.request(path, body, RequestMethod.Delete);
  }

  private request(
    path: string,
    body: any,
    method = RequestMethod.Post,
    custemHeaders?: HttpHeaders
  ): Observable<any> {
    const req = new HttpRequest(method, path, body, {
      headers: custemHeaders || this.headers,
    });

    return (
      this.http
        .request(req)
        .pipe(filter((response) => response instanceof HttpResponse))
        /*.pipe(map((response: HttpResponse<any>) => response.body))*/ //TODO error je
        .pipe(catchError((error) => this.checkError(error)))
    );
  }

  private checkError(error: any): any {
    throw error;
  }

  private serialize(obj: any): HttpParams {
    let params = new HttpParams();

    for (const key in obj) {
      if (obj.hasOwnProperty(key) && !this.looseInvalid(obj[key])) {
        params = params.set(key, obj[key]);
      }
    }

    return params;
  }

  private looseInvalid(a: string | number): boolean {
    return a === '' || a === null || a === undefined;
  }
}
