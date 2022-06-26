import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../root/material-module';
import { ReplaceUnderscorePipe } from './pipes/replace-underscore/replace-underscore.pipe';
import { ProductsComponent } from './components/products/products.component';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { LogoutDialogComponent } from './components/logout-dialog/logout-dialog.component';
import { RoutineProductsCardComponent } from './components/routine-products-card/routine-products-card.component';
import { PatientRoutinesAccordionComponent } from './components/patient-routines-accordion/patient-routines-accordion.component';
import { ReactionDialogComponent } from './components/reaction-dialog/reaction-dialog.component';

@NgModule({
  declarations: [
    ReplaceUnderscorePipe,
    PatientRoutinesAccordionComponent,
    ProductsComponent,
    ProductCardComponent,
    LogoutDialogComponent,
    RoutineProductsCardComponent,
    ReactionDialogComponent,
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
    PatientRoutinesAccordionComponent,
    ReplaceUnderscorePipe,
    ProductsComponent,
    LogoutDialogComponent,
    RoutineProductsCardComponent,
  ],
})
export class SharedModule {}
