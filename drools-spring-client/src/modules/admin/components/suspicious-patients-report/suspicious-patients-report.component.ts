import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Patient } from 'src/modules/shared/models/patient';

@Component({
  selector: 'app-suspicious-patients-report',
  templateUrl: './suspicious-patients-report.component.html',
  styleUrls: ['./suspicious-patients-report.component.scss'],
})
export class SuspiciousPatientsReportComponent implements OnInit {
  @Input()
  data!: Array<Patient>;

  dataSource!: MatTableDataSource<Patient>;

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  displayedColumns: string[] = [
    'Username',
    'Name',
    'Surname',
    'Email',
    'Birthday',
    'Gender',
  ];

  constructor(private liveAnnouncer: LiveAnnouncer) {}

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<Patient>(this.data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this.liveAnnouncer.announce(`Sorted ${sortState.direction} ending`);
    } else {
      this.liveAnnouncer.announce('Sorting cleared');
    }
  }
}
