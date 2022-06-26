import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { AuthService } from 'src/modules/auth/service/auth-service/auth.service';
import { ProductWithIngredients } from 'src/modules/shared/models/product-with-ingredients';
import { ProductService } from 'src/modules/shared/services/product-service/product.service';
import { AddProductFormComponent } from '../../../dermatologist/components/add-product-form/add-product-form.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  role!: String | null;
  username!: string;

  dataSource!: MatTableDataSource<ProductWithIngredients>;
  observable!: Observable<any>;

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(
    private productService: ProductService,
    private toastr: ToastrService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private autService: AuthService
  ) {}

  ngOnInit(): void {
    this.productService.getAll().subscribe((response) => {
      this.setData(response.body);
    });
    this.role = this.autService.getRole();
    this.username = this.autService.getUsername();
  }

  setData(data: ProductWithIngredients[]) {
    data.sort((a, b) => a.name.localeCompare(b.name));
    this.dataSource = new MatTableDataSource<ProductWithIngredients>(data);
    this.dataSource.paginator = this.paginator;
    this.observable = this.dataSource.connect();
  }

  onDeleted(id: Number) {
    /*
    TODO, aslo check role
    this.productService.deleteProduct(id).subscribe({
      next: (success) => {
        this.toastr.success('Successfully deleted ' + success.name);
        this.filterData(id);
      },
      error: (error) => {
        this.toastr.error('Unable to delete item');
      },
    });
    */
  }

  // TODO
  filterData(id: Number) {
    this.dataSource.data = this.dataSource.data.filter(
      (item) => item.id !== id
    );
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '85%';

    const dialogRef = this.dialog.open(AddProductFormComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (result) => {
        if (result == null) return;
        const data = this.dataSource.data;
        data.push(result.body as ProductWithIngredients);
        console.log(result.body);
        this.dataSource.data = data;
      },
      error: (error) => {
        this.toastr.error('Unable to add new product');
        console.log(error);
      },
    });
  }
}
