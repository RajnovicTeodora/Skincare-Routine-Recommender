import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-reaction-dialog',
  templateUrl: './reaction-dialog.component.html',
  styleUrls: ['./reaction-dialog.component.scss'],
})
export class ReactionDialogComponent implements OnInit {
  symptomForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<ReactionDialogComponent>
  ) {
    this.symptomForm = this.fb.group({
      symptom: [
        null,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
        ],
      ],
    });
  }

  ngOnInit(): void {}

  onCloseClick(): void {
    this.dialogRef.close({ data: null });
  }

  onOkClick(): void {
    this.dialogRef.close({ data: this.symptomForm.value.symptom });
  }
}
