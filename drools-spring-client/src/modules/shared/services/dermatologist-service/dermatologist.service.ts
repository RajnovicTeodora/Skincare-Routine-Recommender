import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../../models/user';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class DermatologistService {
  constructor(private apiService: ApiService) {}

  register(dermatologist: User) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.registerDermatologistUrl}`,
      dermatologist
    );
  }
}
