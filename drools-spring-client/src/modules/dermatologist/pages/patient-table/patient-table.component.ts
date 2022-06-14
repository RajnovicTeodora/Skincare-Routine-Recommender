import { LiveAnnouncer } from '@angular/cdk/a11y';
import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Patient } from 'src/modules/shared/models/Patient';

import { PatientService } from 'src/modules/shared/services/patient-service/patient.service';

@Component({
  selector: 'app-patient-table',
  templateUrl: './patient-table.component.html',
  styleUrls: ['./patient-table.component.scss'],
})
export class PatientTableComponent implements OnInit {
  data: Patient[];
  dataSource!: MatTableDataSource<Patient>;
  messageForDialog = '';

  @Output() onPatientRoutinesClicked: EventEmitter<String> =
    new EventEmitter<String>();

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  displayedColumns: string[] = [
    'Username',
    'Name',
    'Surname',
    'Email',
    'Birthday',
    'Gender',
    'Routines',
  ];

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    public router: Router,
    private toastr: ToastrService,
    private liveAnnouncer: LiveAnnouncer
  ) {
    this.data = [];
  }

  ngOnInit(): void {
    this.patientService.getAll().subscribe((response) => {
      this.setData(response.body);
    });
  }

  setData(data: Patient[]) {
    this.dataSource = new MatTableDataSource<Patient>(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this.liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this.liveAnnouncer.announce('Sorting cleared');
    }
  }

  onOpenRoutines(username: String) {
    this.onPatientRoutinesClicked.emit(username);
  }
}
