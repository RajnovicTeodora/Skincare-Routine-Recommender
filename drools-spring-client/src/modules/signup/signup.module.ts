import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './pages/signup/signup.component';
import { MaterialModule } from '../root/material-module';
import { RouterModule } from '@angular/router';
import { SignupRoutes } from './signup.routes';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [SignupComponent],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(SignupRoutes),
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class SignupModule {}
