import { Routes } from '@angular/router';
import { RoleGuard } from '../auth/guards/role/role.guard';
import { PatientDashboardComponent } from './pages/patient-dashboard/patient-dashboard.component';

export const PatientRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: PatientDashboardComponent,
    canActivate: [RoleGuard],
    data: { roles: ['PATIENT'] },
  },
];
