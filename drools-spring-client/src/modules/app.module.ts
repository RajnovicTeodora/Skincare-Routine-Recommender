import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CookieService } from 'ngx-cookie-service';
import { AdminModule } from './admin/admin.module';
import { AppRoutingModule } from './root/app-routing.module';
import { AppComponent } from './root/app.component';
import { MaterialModule } from './root/material-module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TokenInterceptor } from './shared/interceptor/interceptor.interceptor';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AdminModule,
    MaterialModule,
    SharedModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    CookieService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
