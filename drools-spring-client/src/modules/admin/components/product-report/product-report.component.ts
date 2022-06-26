import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/modules/shared/models/product';
import { HelperService } from 'src/modules/shared/services/helper-service/helper.service';

@Component({
  selector: 'app-product-report',
  templateUrl: './product-report.component.html',
  styleUrls: ['./product-report.component.scss'],
})
export class ProductReportComponent implements OnInit {
  @Input() bestProduct!: Product;
  @Input() worstProduct!: Product;

  constructor(private helperService: HelperService) {}

  ngOnInit(): void {}

  bypassUrlSecurity(image: String) {
    return this.helperService.bypassUrlSecurity(image);
  }
}
