import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { PatientService } from 'src/modules/shared/services/patient-service/patient.service';
import { Patient } from 'src/modules/shared/models/patient';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;

  maxDate: Date = new Date();

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private patientService: PatientService,
    public router: Router
  ) {
    this.signupForm = this.fb.group({
      username: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
        ],
      ],
      password: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
        ],
      ],
      name: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
        ],
      ],
      surname: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
        ],
      ],
      email: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
          Validators.email,
        ],
      ],
      birthday: [null, Validators.required],
      gender: [null, Validators.required],
    });
  }

  ngOnInit(): void {}

  saveEmployee() {
    const patient: Patient = {
      ...this.signupForm.value,
    };
    console.log(patient);
    this.patientService.register(patient).subscribe({
      next: () => {
        this.toastr.success('Successfully created a new account');
        this.router.navigate(['/skincare/auth/login']);
      },
      error: (error) => {
        this.toastr.error('Unable to create a new account');
        console.log(error);
      },
    });
  }

  cancel() {
    this.router.navigate(['/skincare/auth/login']);
  }
}
