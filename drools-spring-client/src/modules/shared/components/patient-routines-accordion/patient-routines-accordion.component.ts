import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatAccordion } from '@angular/material/expansion';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { RoutineWithReaction } from '../../models/routine-with-reaction';
import { RoutineService } from '../../services/routine-service/routine.service';

@Component({
  selector: 'app-patient-routines-accordion',
  templateUrl: './patient-routines-accordion.component.html',
  styleUrls: ['./patient-routines-accordion.component.scss'],
})
export class PatientRoutinesAccordionComponent implements OnInit {
  dataSource!: MatTableDataSource<RoutineWithReaction>;
  @Input()
  username!: String | null;

  routines!: Array<RoutineWithReaction>;

  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(
    private routineService: RoutineService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    console.log(this.username);
    if (this.username == null) return;
    this.routineService.getRoutinesWithReaction(this.username).subscribe({
      next: (response) => {
        this.setData(response.body);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  setData(data: RoutineWithReaction[]) {
    this.routines = data as Array<RoutineWithReaction>;
    this.routines.sort((a, b) => (a.startDate > b.startDate ? -1 : 1));
  }

  updateData(routine: RoutineWithReaction) {
    const data = this.routines;
    data.push(routine);
    this.routines = data;
    this.routines.sort((a, b) => (a.startDate > b.startDate ? -1 : 1));
  }
}
