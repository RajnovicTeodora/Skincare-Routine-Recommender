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
import { SharedModule } from '../shared/shared.module';
import { ProductCardComponent } from '../shared/components/product-card/product-card.component';
import { AddProductFormComponent } from './components/add-product-form/add-product-form.component';

@NgModule({
  declarations: [
    DermatologistDashboardComponent,
    PatientTableComponent,
    PatientRoutinesComponent,
    RoutineFormComponent,
    AddProductFormComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(DermatologistRoutes),
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class DermatologistModule {}
