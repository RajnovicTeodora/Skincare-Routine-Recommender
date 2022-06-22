import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../../models/user';
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

  edit(user: User) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.editUserUrl}`,
      user
    );
  }
}
