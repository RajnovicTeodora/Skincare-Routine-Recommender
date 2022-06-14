import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { ReccomendRoutine } from 'src/modules/shared/models/recommend-routine';
import { Select } from 'src/modules/shared/models/select';
import { RoutineService } from 'src/modules/shared/services/routine-service/routine.service';

@Component({
  selector: 'app-routine-form',
  templateUrl: './routine-form.component.html',
  styleUrls: ['./routine-form.component.scss'],
})
export class RoutineFormComponent implements OnInit {
  routineForm: FormGroup;
  username: String = '';

  skinCharacteristicsSelect: Select[] = [
    { value: 'OILY_SKIN', viewValue: 'Oily skin' },
    { value: 'VISIBLE_PORES', viewValue: 'Visible pores' },
    { value: 'TIGHT_SKIN', viewValue: 'Tight skin' },
    { value: 'DRY_SKIN', viewValue: 'Dry skin' },
    { value: 'BLEMISHES', viewValue: 'Blemises' },
    { value: 'ITCHY_SKIN', viewValue: 'Itchy skin' },
    { value: 'RED_PATCHES', viewValue: 'Red patches' },
    { value: 'RADIANT_COMPLEXION', viewValue: 'Radiant complexion' },
  ];
  goalsSelect: Select[] = [
    { value: 'REDUCE_REDNESS', viewValue: 'Reduce redness' },
    { value: 'REDUCE_BLACKHEADS', viewValue: 'Reduce blackheads' },
    { value: 'REDUCE_OILINESS', viewValue: 'Reduce oiliness' },
  ];

  acneTypeSelect: Select[] = [
    { value: 'BLACKHEADS', viewValue: 'Blackheads' },
    { value: 'WHITEHEADS', viewValue: 'Whiteheads' },
    { value: 'PAPULES', viewValue: 'Papules' },
    { value: 'PUSTULES', viewValue: 'Pustules' },
    { value: 'CYSTS', viewValue: 'Cysts' },
    { value: 'NODULES', viewValue: 'Nodules' },
  ];

  allergies: Array<String>;

  findAllergyIndex(allergyToBeFound: String) {
    return this.allergies.indexOf(allergyToBeFound);
  }

  deleteAllergy(allergyToBeDeleted: String) {
    const index = this.findAllergyIndex(allergyToBeDeleted);
    if (index != -1) delete this.allergies[index];
  }

  addAllergy() {
    const allergyToBeAdded: String = this.routineForm.value.allergy;
    if (allergyToBeAdded === null || allergyToBeAdded.match(/^ *$/) !== null)
      return;
    const index = this.findAllergyIndex(allergyToBeAdded);
    if (index == -1) this.allergies.push(allergyToBeAdded);
    this.routineForm.value.allergy = '';
  }

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private routineService: RoutineService,
    private dialogRef: MatDialogRef<RoutineFormComponent>
  ) {
    this.routineForm = this.fb.group({
      skinCharacteristics: [null, Validators.required],
      wantedGoals: [null, Validators.required],
      acneType: [null, Validators.required],
      allergy: [null],
      manufacturer: [null, Validators.required],
    });
    this.allergies = new Array<String>();
  }

  ngOnInit(): void {}

  getRoutineRecommendation() {
    const routineInput: ReccomendRoutine = {
      patientUsername: this.username,
      skinCharacteristics: this.routineForm.value.skinCharacteristics,
      wantedGoals: this.routineForm.value.wantedGoals,
      acneType: this.routineForm.value.acneType,
      allergies: this.allergies,
      manufacturer: this.routineForm.value.manufacturer,
    };
    console.log(routineInput);

    this.routineService.getRoutinePerscription(routineInput).subscribe({
      next: (success) => {
        this.toastr.success('Successfully added and approved ' + success.name);
        this.dialogRef.close(success);
      },
      error: (error) => {
        this.toastr.error('Unable to add new item');
        console.log(error);
      },
    });
  }

  cancel() {
    this.dialogRef.close();
  }
}
