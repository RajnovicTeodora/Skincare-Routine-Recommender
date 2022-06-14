import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class RoutineService {
  constructor(private apiService: ApiService) {}

  getRoutinePerscription(routineInput: any) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.getRoutinePerscriptionUrl}`,
      routineInput
    );
  }
}
