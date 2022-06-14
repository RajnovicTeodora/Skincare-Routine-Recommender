import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RoutineFormComponent } from '../../components/routine-form/routine-form.component';

@Component({
  selector: 'app-patient-routines',
  templateUrl: './patient-routines.component.html',
  styleUrls: ['./patient-routines.component.scss'],
})
export class PatientRoutinesComponent implements OnInit {
  @Input()
  username!: String | null;

  constructor(
    //private itemService: ItemService,
    private toastr: ToastrService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {}

  openDialog() {
    if (this.username == null) return;
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '70%';

    const dialogRef = this.dialog.open(RoutineFormComponent, dialogConfig);
    let instance = dialogRef.componentInstance;
    instance.username = this.username;
    dialogRef.afterClosed().subscribe({
      next: () => {
        //this.setAll();
      },
      error: (error) => {
        this.toastr.error('Unable to create new routine');
        console.log(error);
      },
    });
  }
}
