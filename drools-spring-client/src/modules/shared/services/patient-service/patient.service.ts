import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Patient } from '../../models/patient';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  constructor(private apiService: ApiService) {}

  getAll() {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.getPatientsUrl}`
    );
  }

  register(patient: Patient) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.registerPatientUrl}`,
      patient
    );
  }

  getByUsername(username: String) {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.getPatientByUsernameUrl}/${username}`
    );
  }
}
