import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { NewProduct } from '../../models/new-product';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private apiService: ApiService) {}

  getAll() {
    return this.apiService.get(
      `${environment.baseUrl}/${environment.getProductsUrl}`
    );
  }

  addProduct(product: NewProduct) {
    return this.apiService.post(
      `${environment.baseUrl}/${environment.addProductUrl}`,
      product
    );
  }
}
