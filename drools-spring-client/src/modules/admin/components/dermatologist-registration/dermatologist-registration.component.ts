import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/modules/shared/models/user';
import { DermatologistService } from 'src/modules/shared/services/dermatologist-service/dermatologist.service';

@Component({
  selector: 'app-dermatologist-registration',
  templateUrl: './dermatologist-registration.component.html',
  styleUrls: ['./dermatologist-registration.component.scss'],
})
export class DermatologistRegistrationComponent implements OnInit {
  dermatologistForm: FormGroup;

  maxDate: Date = new Date();

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private dermatologistService: DermatologistService,
    private dialogRef: MatDialogRef<DermatologistRegistrationComponent>
  ) {
    this.dermatologistForm = this.fb.group({
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
    });
  }

  ngOnInit(): void {}

  saveDermatologist() {
    const user: User = {
      ...this.dermatologistForm.value,
    };
    console.log(user);
    this.dermatologistService.register(user).subscribe({
      next: (success) => {
        this.toastr.success('Successfully added a new dermatologist');
        this.dialogRef.close(success.body);
      },
      error: (error) => {
        this.toastr.error('Unable to add a new dermatologist');
        console.log(error);
      },
    });
  }

  cancel() {
    this.dialogRef.close();
  }
}
