import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/modules/auth/service/auth-service/auth.service';
import { LoggedInUser } from 'src/modules/shared/models/logged-in-user';

@Component({
  selector: 'app-dermatologist-dashboard',
  templateUrl: './dermatologist-dashboard.component.html',
  styleUrls: ['./dermatologist-dashboard.component.scss'],
})
export class DermatologistDashboardComponent implements OnInit {
  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  user: LoggedInUser;
  showModalLogout: boolean;
  showModalPasswordChange: boolean;
  activeView: string = '1';
  showRoutinesUsername: String | null = null;

  constructor(
    private observer: BreakpointObserver,
    public router: Router,
    private authService: AuthService
  ) {
    this.user = new BehaviorSubject<LoggedInUser>(
      (this.user = this.authService.getLoggedInUser())
    ).value;
    this.showModalPasswordChange = false;
    this.showModalLogout = false;
  }

  ngOnInit() {}

  ngAfterViewInit() {
    setTimeout(() => {
      this.observer.observe(['(max-width: 800px)']).subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });
    });
  }

  onLogoutButtonClicked() {
    this.showModalLogout = true;
  }

  onLogoutCloseClicked() {
    this.showModalLogout = false;
  }

  onPasswordChangeButtonClicked() {
    this.showModalPasswordChange = true;
  }

  onPatientRoutinesClicked(username: String) {
    this.activeView = '2';
    this.showRoutinesUsername = username;
  }
}
