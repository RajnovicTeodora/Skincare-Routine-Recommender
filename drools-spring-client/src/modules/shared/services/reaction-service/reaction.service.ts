import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CheckReaction } from '../../models/check-reaction';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class ReactionService {
  constructor(private apiService: ApiService) {}

  hasReaction(username: String, product: Number) {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.hasReactionUrl}/${username}/${product}`
    );
  }

  checkReaction(checkReaction: CheckReaction) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.checkProductReactionUrl}`,
      checkReaction
    );
  }
}
