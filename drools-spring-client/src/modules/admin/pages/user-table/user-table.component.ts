import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/modules/auth/service/auth-service/auth.service';
import { SearchUsers } from 'src/modules/shared/models/search/search-users';
import { Select } from 'src/modules/shared/models/select';
import { User } from 'src/modules/shared/models/user';
import { UserService } from 'src/modules/shared/services/user-service/user.service';
import { DermatologistRegistrationComponent } from '../../components/dermatologist-registration/dermatologist-registration.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss'],
})
export class UserTableComponent implements OnInit {
  username!: String;

  dataSource!: MatTableDataSource<User>;
  messageForDialog = '';
  searchForm: FormGroup;

  @ViewChild(UserTableComponent)
  userTableComponent!: UserTableComponent;

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  roles: Select[] = [
    { value: '', viewValue: 'All' },
    { value: 'PATIENT', viewValue: 'Patient' },
    { value: 'DERMATOLOGIST', viewValue: 'Dermatologist' },
    { value: 'ADMIN', viewValue: 'Admin' },
  ];

  displayedColumns: string[] = ['Username', 'Name', 'Surname', 'Email', 'Role'];

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService,
    private liveAnnouncer: LiveAnnouncer,
    private dialog: MatDialog,
    private authService: AuthService
  ) {
    this.username = authService.getUsername();
    this.searchForm = this.fb.group({
      search: [''],
      role: [''],
    });
  }

  ngOnInit(): void {
    this.userService.getAll(this.username).subscribe((response) => {
      this.setData(response.body);
    });
  }

  setData(data: User[]) {
    this.dataSource = new MatTableDataSource<User>(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  search() {
    const search: SearchUsers = {
      ...this.searchForm.value,
    };

    this.userService.getAll(this.username, search).subscribe((response) => {
      this.dataSource.data = response.body;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
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
    dialogConfig.width = '40%';
    dialogConfig.height = '70%';

    const dialogRef = this.dialog.open(
      DermatologistRegistrationComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe({
      next: (result) => {
        if (result == null) return;

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
