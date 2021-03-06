import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';

const routes: Routes = [
  {
    path: 'skincare',
    component: AppComponent,
    children: [
      {
        path: 'admin',
        loadChildren: () =>
          import('./../admin/admin.module').then((m) => m.AdminModule),
      },
      {
        path: 'dermatologist',
        loadChildren: () =>
          import('./../dermatologist/dermatologist.module').then(
            (m) => m.DermatologistModule
          ),
      },
      {
        path: 'patient',
        loadChildren: () =>
          import('./../patient/patient.module').then((m) => m.PatientModule),
      },
      {
        path: 'auth',
        loadChildren: () =>
          import('./../auth/auth.module').then((m) => m.AuthModule),
      },
      {
        path: 'signup',
        loadChildren: () =>
          import('./../signup/signup.module').then((m) => m.SignupModule),
      },
    ],
  },
  {
    path: '',
    redirectTo: 'skincare/auth/login',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
