import { Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';

export const SignupRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: SignupComponent,
  },
];
