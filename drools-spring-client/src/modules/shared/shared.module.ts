import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../root/material-module';
import { ReplaceUnderscorePipe } from './pipes/replace-underscore/replace-underscore.pipe';
import { PatientRoutinesTableComponent } from './components/patient-routines-table/patient-routines-table.component';

@NgModule({
  declarations: [ReplaceUnderscorePipe, PatientRoutinesTableComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
  ],
})
export class SharedModule {}
