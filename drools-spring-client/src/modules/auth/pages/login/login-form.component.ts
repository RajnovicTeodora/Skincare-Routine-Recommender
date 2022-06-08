import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Subject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../service/auth-service/auth.service';
import { UserService } from 'src/modules/shared/services/user-service/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss'],
})
export class LoginFormComponent implements OnInit {
  loginForm: FormGroup;
  @Output() onLoginClose = new EventEmitter();

  private ngUnsubscribe: Subject<void> = new Subject<void>();
  hide = true;

  regex = '^(?!<.+?>).*$';

  constructor(
    private fb: FormBuilder,
    public router: Router,
    private toastr: ToastrService,
    private authService: AuthService,
    private userService: UserService,
    private route: ActivatedRoute
  ) {
    this.loginForm = this.fb.group({
      username: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(15),
          Validators.pattern(this.regex),
        ]),
      ],
      password: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
          Validators.pattern('^(?!<+?>).*$'),
        ]),
      ],
    });
    const loggedUser = this.userService.currentUser;
  }

  ngOnInit() {}

  login() {
    const loggedUser = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password,
    };

    this.authService.login(loggedUser).subscribe({
      next: (result) => {
        this.toastr.success('Successful login!');
        this.loginForm.reset();
        console.log(this.authService.getRole());
        if (this.authService.getRole() == 'ADMIN') {
          console.log('cao');
          this.router.navigate(['skincare/admin']);
        } else {
        }
      },
      error: (error) => {
        this.toastr.error(error.error);
      },
    });
  }
}
