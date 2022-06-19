import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/modules/auth/service/auth-service/auth.service';
import { User } from 'src/modules/shared/models/user';
import { UserService } from 'src/modules/shared/services/user-service/user.service';
import { DermatologistRegistrationComponent } from '../../components/dermatologist-registration/dermatologist-registration.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss'],
})
export class UserTableComponent implements OnInit {
  username!: String | null;

  data: User[];
  dataSource!: MatTableDataSource<User>;
  messageForDialog = '';

  @ViewChild(UserTableComponent)
  userTableComponent!: UserTableComponent;

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  displayedColumns: string[] = ['Username', 'Name', 'Surname', 'Email'];

  constructor(
    private userService: UserService,
    public router: Router,
    private toastr: ToastrService,
    private liveAnnouncer: LiveAnnouncer,
    private dialog: MatDialog,
    private authService: AuthService
  ) {
    this.data = [];
    this.username = authService.getUsername();
  }

  ngOnInit(): void {
    if (this.username == null) return;
    this.userService.getAll(this.username).subscribe((response) => {
      this.setData(response.body);
    });
  }

  setData(data: User[]) {
    this.dataSource = new MatTableDataSource<User>(data);
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

  openDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '70%';

    const dialogRef = this.dialog.open(
      DermatologistRegistrationComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe({
      next: (result) => {
        const data = this.dataSource.data;
        data.push(result as User);
        this.dataSource.data = data;
      },
      error: (error) => {
        this.toastr.error('Unable to add new dermatologist');
        console.log(error);
      },
    });
  }
}
