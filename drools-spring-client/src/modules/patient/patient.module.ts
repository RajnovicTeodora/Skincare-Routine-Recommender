import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientDashboardComponent } from './pages/patient-dashboard/patient-dashboard.component';
import { MaterialModule } from '../root/material-module';
import { PatientRoutes } from './patient.routes';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [PatientDashboardComponent],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(PatientRoutes),
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class PatientModule {}
