import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/modules/auth/service/auth-service/auth.service';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-logout-dialog',
  templateUrl: './logout-dialog.component.html',
  styleUrls: ['./logout-dialog.component.scss'],
})
export class LogoutDialogComponent implements OnInit {
  @Output() onLogoutClose = new EventEmitter();

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    public router: Router
  ) {}

  ngOnInit(): void {}

  cancel() {
    this.onLogoutClose.emit(true);
  }

  confirm() {
    this.authService.logout().subscribe({
      next: () => {
        localStorage.clear();
        this.toastr.success('Successfully logged out!');
        this.router.navigate(['/skincare/auth/login']);
        this.onLogoutClose.emit(true);
      },
      error: (error) => {
        this.toastr.error(error.error);
      },
    });
  }
}
