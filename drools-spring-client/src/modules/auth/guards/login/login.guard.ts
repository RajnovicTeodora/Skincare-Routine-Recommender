import { Injectable } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';

import { AuthService } from '../../service/auth-service/auth.service';

@Injectable({ providedIn: 'root' })
export class LoginGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const token = this.authenticationService.tokenIsPresent();
    if (token) {
      // Authorised
      return true;
    }

    // Redirect to login
    this.router.navigate(['skincare/login'], {
      queryParams: { returnUrl: state.url },
    });
    return false;
  }
}
