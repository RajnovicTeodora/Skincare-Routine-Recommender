import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  currentUser: any;

  constructor(private apiService: ApiService) {}

  getAll(username: String) {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.getUsersUrl}/${username}`
    );
  }
}
