import { Component, Input, OnInit } from '@angular/core';
import { ProductReaction } from 'src/modules/shared/models/product-reaction';
import { HelperService } from 'src/modules/shared/services/helper-service/helper.service';

@Component({
  selector: 'app-routine-products-card',
  templateUrl: './routine-products-card.component.html',
  styleUrls: ['./routine-products-card.component.scss'],
})
export class RoutineProductsCardComponent implements OnInit {
  @Input() public products!: Array<ProductReaction>;
  constructor(private helperService: HelperService) {}

  ngOnInit(): void {}

  bypassUrlSecurity(image: String) {
    return this.helperService.bypassUrlSecurity(image);
  }
}
