import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { PatientRoutinesAccordionComponent } from 'src/modules/shared/components/patient-routines-accordion/patient-routines-accordion.component';
import { RoutineWithReaction } from 'src/modules/shared/models/routine-with-reaction';
import { RoutineFormComponent } from '../../components/routine-form/routine-form.component';

@Component({
  selector: 'app-patient-routines',
  templateUrl: './patient-routines.component.html',
  styleUrls: ['./patient-routines.component.scss'],
})
export class PatientRoutinesComponent implements OnInit {
  @Input()
  username!: String | null;

  @ViewChild(PatientRoutinesAccordionComponent)
  patientRoutinesAccordionComponent!: PatientRoutinesAccordionComponent;

  constructor(private toastr: ToastrService, private dialog: MatDialog) {}

  ngOnInit(): void {}

  openDialog() {
    if (this.username == null) return;
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '36%';
    dialogConfig.height = '65%';

    const dialogRef = this.dialog.open(RoutineFormComponent, dialogConfig);
    let instance = dialogRef.componentInstance;
    instance.username = this.username;
    dialogRef.afterClosed().subscribe({
      next: (result) => {
        if (result != null)
          this.patientRoutinesAccordionComponent.updateData(
            result as RoutineWithReaction
          );
      },
      error: (error) => {
        this.toastr.error('Unable to create new routine');
        console.log(error);
      },
    });
  }
}
