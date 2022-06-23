import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../../models/user';
import { SearchUsers } from '../../models/search/search-users';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  currentUser: any;

  constructor(private apiService: ApiService) {}

  getAll(username: String, searchUsers?: SearchUsers) {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.getUsersUrl}/${username}`,
      { search: searchUsers?.search, role: searchUsers?.role }
    );
  }

  edit(user: User) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.editUserUrl}`,
      user
    );
  }
}
