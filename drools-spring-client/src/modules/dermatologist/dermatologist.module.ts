import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DermatologistDashboardComponent } from './pages/dermatologist-dashboard/dermatologist-dashboard.component';
import { MaterialModule } from '../root/material-module';
import { DermatologistRoutes } from './dermatologist.routes';
import { RouterModule } from '@angular/router';
import { PatientTableComponent } from './pages/patient-table/patient-table.component';
import { PatientRoutinesComponent } from './pages/patient-routines/patient-routines.component';
import { RoutineFormComponent } from './components/routine-form/routine-form.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    DermatologistDashboardComponent,
    PatientTableComponent,
    PatientRoutinesComponent,
    RoutineFormComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(DermatologistRoutes),

    FormsModule,
    ReactiveFormsModule,
  ],
})
export class DermatologistModule {}
