import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class IngredientService {
  constructor(private apiService: ApiService) {}

  getAll() {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.getIngredientsUrl}`
    );
  }
}
