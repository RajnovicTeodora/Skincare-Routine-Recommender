import { Routes } from '@angular/router';
import { RoleGuard } from '../auth/guards/role/role.guard';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';

export const AdminRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: AdminDashboardComponent,
    canActivate: [RoleGuard],
    data: { roles: ['ADMIN'] },
  },
];
