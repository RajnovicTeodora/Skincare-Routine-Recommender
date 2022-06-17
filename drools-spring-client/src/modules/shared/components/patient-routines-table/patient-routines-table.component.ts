import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { RoutineWithReaction } from '../../models/routine-with-reaction';
import { RoutineService } from '../../services/routine-service/routine.service';
import { RoutineProductsWithReactionComponent } from '../routine-products-with-reaction/routine-products-with-reaction.component';

@Component({
  selector: 'app-patient-routines-table',
  templateUrl: './patient-routines-table.component.html',
  styleUrls: ['./patient-routines-table.component.scss'],
})
export class PatientRoutinesTableComponent implements OnInit {
  dataSource!: MatTableDataSource<RoutineWithReaction>;
  @Input()
  username!: String | null;

  displayedColumns: string[] = ['StartDate', 'Products'];

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  constructor(
    private routineService: RoutineService,
    private toastr: ToastrService,
    private dialog: MatDialog,
    private liveAnnouncer: LiveAnnouncer
  ) {}

  ngOnInit(): void {
    console.log(this.username);
    if (this.username == null) return;
    this.routineService
      .getRoutinesWithReaction(this.username)
      .subscribe((response) => {
        this.setData(response.body);
      });
  }

  setData(data: RoutineWithReaction[]) {
    data.sort((a, b) => (a.startDate > b.startDate ? 1 : -1));
    this.dataSource = new MatTableDataSource<RoutineWithReaction>(data);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    //this.observable = this.dataSource.connect();
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this.liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this.liveAnnouncer.announce('Sorting cleared');
    }
  }

  onOpenProducts(index: number) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '70%';

    const dialogRef = this.dialog.open(RoutineProductsWithReactionComponent, {
      ...dialogConfig,
      data: {
        products: this.dataSource.data[index].productWithReaction,
      },
    });

    let instance = dialogRef.componentInstance;
    instance.products = this.dataSource.data[index].productWithReaction;

    dialogRef.afterClosed().subscribe({
      next: () => {
        //this.setAll();
      },
    });
  }

  updateData(routine: RoutineWithReaction) {
    const data = this.dataSource.data;
    data.push(routine);
    this.dataSource.data = data;
  }
}
