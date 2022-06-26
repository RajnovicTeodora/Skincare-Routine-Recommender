import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { ReccomendRoutine } from 'src/modules/shared/models/recommend-routine';
import { Select } from 'src/modules/shared/models/select';
import { IngredientService } from 'src/modules/shared/services/ingredient-service/ingredient.service';
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
    { value: 'REDUCE_ACNE', viewValue: 'Reduce acne' },
    { value: 'REDUCE_ACNE_SCARING', viewValue: 'Reduce acne scaring' },
    { value: 'REDUCE_OILINESS', viewValue: 'Reduce oiliness' },
    { value: 'REDUCE_WRINKLES', viewValue: 'Reduce wrinkles' },
    { value: 'REDUCE_SUN_DAMAGE', viewValue: 'Reduce sun damage' },
    { value: 'IMPROVE_PIGMENTATION', viewValue: 'Improve pigmentation' },
    { value: 'HYDRATE_SKIN', viewValue: 'Hydrate skin' },
    { value: 'REDUCE_BREAKOUTS', viewValue: 'Reduce breakouts' },
    { value: 'IMPROVE_SKIN_ELASTICITY', viewValue: 'Improve skin elasticity' },
  ];

  acneTypeSelect: Select[] = [
    { value: 'BLACKHEADS', viewValue: 'Blackheads' },
    { value: 'WHITEHEADS', viewValue: 'Whiteheads' },
    { value: 'PAPULES', viewValue: 'Papules' },
    { value: 'PUSTULES', viewValue: 'Pustules' },
    { value: 'CYSTS', viewValue: 'Cysts' },
    { value: 'NODULES', viewValue: 'Nodules' },
  ];

  allergens: Array<String>;

  regex = '^(?!<.+?>).*$';

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private routineService: RoutineService,
    private ingredientService: IngredientService,
    private dialogRef: MatDialogRef<RoutineFormComponent>
  ) {
    this.routineForm = this.fb.group({
      skinCharacteristics: [null, Validators.required],
      wantedGoals: [null, Validators.required],
      acneType: [null, Validators.required],
      allergies: [null],
      manufacturer: [
        null,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
          Validators.pattern(this.regex),
        ],
      ],
    });
    this.allergens = new Array<String>();
  }

  ngOnInit(): void {
    this.ingredientService.getAll().subscribe({
      next: (value) => {
        value.body.forEach((element: any) => {
          this.allergens.push(element.name);
        });
      },
    });
  }

  getRoutineRecommendation() {
    const routineInput: ReccomendRoutine = {
      patientUsername: this.username,
      skinCharacteristics: this.routineForm.value.skinCharacteristics,
      wantedGoals: this.routineForm.value.wantedGoals,
      acneType: this.routineForm.value.acneType,
      allergies: this.routineForm.value.allergies,
      manufacturer: this.routineForm.value.manufacturer,
    };

    this.routineService.getRoutinePerscription(routineInput).subscribe({
      next: (success) => {
        this.toastr.success('Successfully perscribed a new routine!');
        console.log(success.body);
        this.dialogRef.close(success.body);
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
