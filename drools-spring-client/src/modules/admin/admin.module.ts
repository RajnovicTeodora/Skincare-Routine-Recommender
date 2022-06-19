import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { AdminRoutes } from './admin.routes';
import { RouterModule } from '@angular/router';
import { DermatologistRegistrationComponent } from './components/dermatologist-registration/dermatologist-registration.component';
import { MaterialModule } from '../root/material-module';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserTableComponent } from './pages/user-table/user-table.component';

@NgModule({
  declarations: [AdminDashboardComponent, DermatologistRegistrationComponent, UserTableComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminRoutes),
    MaterialModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AdminModule {}
