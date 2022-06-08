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
        path: 'auth',
        loadChildren: () =>
          import('./../auth/auth.module').then((m) => m.AuthModule),
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
