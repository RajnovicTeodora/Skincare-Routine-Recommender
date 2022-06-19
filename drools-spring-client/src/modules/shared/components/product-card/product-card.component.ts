import { Component, Input, OnInit, Output } from '@angular/core';
import { ProductWithIngredients } from 'src/modules/shared/models/product-with-ingredients';
import { HelperService } from 'src/modules/shared/services/helper-service/helper.service';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss'],
})
export class ProductCardComponent implements OnInit {
  @Input()
  product!: ProductWithIngredients;

  @Input()
  role!: String | null;

  @Output() deleteClicked = new EventEmitter();

  constructor(private helperService: HelperService) {}

  ngOnInit() {}

  deleteItem(id: Number) {
    this.deleteClicked.emit(id);
  }

  bypassUrlSecurity(image: String) {
    return this.helperService.bypassUrlSecurity(image);
  }
}
