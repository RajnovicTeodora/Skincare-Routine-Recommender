import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../root/material-module';
import { ReplaceUnderscorePipe } from './pipes/replace-underscore/replace-underscore.pipe';
import { PatientRoutinesTableComponent } from './components/patient-routines-table/patient-routines-table.component';
import { RoutineProductsWithReactionComponent } from './components/routine-products-with-reaction/routine-products-with-reaction.component';
import { ProductsComponent } from './pages/products/products.component';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { LogoutDialogComponent } from './components/logout-dialog/logout-dialog.component';

@NgModule({
  declarations: [
    ReplaceUnderscorePipe,
    PatientRoutinesTableComponent,
    RoutineProductsWithReactionComponent,
    ReplaceUnderscorePipe,
    ProductsComponent,
    ProductCardComponent,
    LogoutDialogComponent,
  ],
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
    MaterialModule,
  ],
  exports: [
    PatientRoutinesTableComponent,
    ReplaceUnderscorePipe,
    ProductsComponent,
    LogoutDialogComponent,
  ],
})
export class SharedModule {}
