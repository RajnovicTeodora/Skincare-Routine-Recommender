import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AuthRoutes } from './auth.routes';
import { MaterialModule } from '../root/material-module';
import { LoginFormComponent } from './pages/login/login-form.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [LoginFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(AuthRoutes),
    HttpClientModule,
    MaterialModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
  ],
  exports: [LoginFormComponent],
})
export class AuthModule {}
