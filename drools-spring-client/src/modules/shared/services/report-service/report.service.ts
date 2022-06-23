import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  constructor(private apiService: ApiService) {}

  getSusPatients() {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.suspiciousPatientsReportUrl}`
    );
  }

  getProductReport() {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.productReportUrl}`
    );
  }
}
