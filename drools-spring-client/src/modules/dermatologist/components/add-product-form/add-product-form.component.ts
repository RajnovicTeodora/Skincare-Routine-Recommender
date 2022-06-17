import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { NewProduct } from 'src/modules/shared/models/new-product';
import { Select } from 'src/modules/shared/models/select';
import { IngredientService } from 'src/modules/shared/services/ingredient-service/ingredient.service';
import { ProductService } from 'src/modules/shared/services/product-service/product.service';

@Component({
  selector: 'app-add-product-form',
  templateUrl: './add-product-form.component.html',
  styleUrls: ['./add-product-form.component.scss'],
})
export class AddProductFormComponent implements OnInit {
  addProductForm: FormGroup;
  isImageSaved: boolean = false;
  url: any;

  productTypes: Select[] = [
    { value: 'CLEANSER', viewValue: 'Cleanser' },
    { value: 'EXFOLIATOR', viewValue: 'Exfoliator' },
    { value: 'SERUM', viewValue: 'Serum' },
    { value: 'SUN_SCREEN', viewValue: 'Sun screen' },
    { value: 'MOISTURIZER', viewValue: 'Moisturizer' },
  ];

  ingredientsSelect: Select[] = [];

  regex = '^(?!<.+?>).*$';

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private ingredientService: IngredientService,
    private productService: ProductService,
    private dialogRef: MatDialogRef<AddProductFormComponent>
  ) {
    this.addProductForm = this.fb.group({
      name: [
        null,
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(15),
        Validators.pattern(this.regex),
      ],
      productType: [
        null,
        Validators.required,
        Validators.pattern('CLEANSER|EXFOLIATOR|SERUM|SUN_SCREEN|MOISTURIZER'),
      ],
      manufacturer: [
        null,
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20),
        Validators.pattern(this.regex),
      ],
      usageInstructions: [
        null,
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(120),
      ],
      //image: [null, Validators.required],
      price: [
        null,
        Validators.required,
        Validators.min(0.1),
        Validators.max(999.99),
      ],
      ingredients: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.ingredientService.getAll().subscribe({
      next: (result) => {
        console.log(result.body);
        result.body.forEach((element: any) => {
          this.ingredientsSelect.push({
            value: element.id,
            viewValue: element.name,
          });
        });
      },
    });
  }

  fileChangeEvent(event: any) {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.url = reader.result;
        this.isImageSaved = true;
      };
    }
  }

  saveProduct() {
    const newProduct: NewProduct = {
      ...this.addProductForm.value,

      image: this.url.split(',')[1],
    };
    this.productService.addProduct(newProduct).subscribe({
      next: (success) => {
        this.toastr.success('Successfully added product: ' + success.name);
        this.dialogRef.close(success);
      },
      error: (error) => {
        this.toastr.error('Unable to add new product');
        console.log(error);
      },
    });
  }

  cancel() {
    this.dialogRef.close();
  }
}
