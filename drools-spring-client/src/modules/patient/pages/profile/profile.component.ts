import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { ToastrService } from 'ngx-toastr';
import { Patient } from 'src/modules/shared/models/patient';
import { User } from 'src/modules/shared/models/user';
import { PatientService } from 'src/modules/shared/services/patient-service/patient.service';
import { UserService } from 'src/modules/shared/services/user-service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  @Input()
  username!: String;
  patient!: Patient;

  editForm!: FormGroup;
  editMode = false;

  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(
    private patientService: PatientService,
    private userService: UserService,
    private fb: FormBuilder,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.patientService.getByUsername(this.username).subscribe({
      next: (success) => {
        this.patient = success.body as Patient;
        this.editForm = this.fb.group({
          name: [
            this.patient.name,
            [
              Validators.required,
              Validators.minLength(3),
              Validators.maxLength(25),
            ],
          ],
          surname: [
            this.patient.surname,
            [
              Validators.required,
              Validators.minLength(3),
              Validators.maxLength(25),
            ],
          ],
          email: [
            this.patient.email,
            [
              Validators.required,
              Validators.minLength(3),
              Validators.maxLength(25),
              Validators.email,
            ],
          ],
          birthday: [this.patient.birthday],
          gender: [this.patient.username],
        });
      },
      error: (error) => {
        // TODO toastr
        console.log(error);
      },
    });
  }

  editProfile() {
    const user: User = {
      username: this.username,
      name: this.editForm.value.name,
      surname: this.editForm.value.surname,
      email: this.editForm.value.email,
    };
    console.log(user);
    this.userService.edit(user).subscribe({
      next: () => {
        this.toastr.success('Successfully edited profile');
      },
      error: (error) => {
        this.toastr.error('Unable to edit profile');
        console.log(error);
      },
    });
  }
}
