import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { AuthService } from '../../service/auth-service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(
    private authenticationService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const roles: string[] = route.data['roles'];
    const currentRole: String | null = this.authenticationService.getRole();
    if (!currentRole) {
      this.router.navigate(['skincare/login'], {
        queryParams: { to: state.url },
      });
      return false;
    }
    let hasAccessRights = false;
    roles.forEach((role) => {
      if (currentRole == role) {
        hasAccessRights = true;
      }
    });
    if (hasAccessRights) {
      return true;
    } else {
      this.toastr.error('Access unauthorized');
      this.router.navigate(['']);
      return false;
    }
  }
}
