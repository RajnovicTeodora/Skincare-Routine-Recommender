import { Routes } from '@angular/router';
import { RoleGuard } from '../auth/guards/role/role.guard';
import { DermatologistDashboardComponent } from './pages/dermatologist-dashboard/dermatologist-dashboard.component';

export const DermatologistRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: DermatologistDashboardComponent,
    canActivate: [RoleGuard],
    data: { roles: ['DERMATOLOGIST'] },
  },
];
