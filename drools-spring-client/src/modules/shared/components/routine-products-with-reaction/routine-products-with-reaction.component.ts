import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductReaction } from '../../models/product-reaction';
import { HelperService } from '../../services/helper-service/helper.service';

@Component({
  selector: 'app-routine-products-with-reaction',
  templateUrl: './routine-products-with-reaction.component.html',
  styleUrls: ['./routine-products-with-reaction.component.scss'],
})
export class RoutineProductsWithReactionComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public products: Array<ProductReaction>,
    private helperService: HelperService
  ) {}

  ngOnInit(): void {}

  bypassUrlSecurity(image: String) {
    return this.helperService.bypassUrlSecurity(image);
  }
}
