import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/modules/shared/models/patient';
import { ProductReport } from 'src/modules/shared/models/product-report';
import { ReportService } from 'src/modules/shared/services/report-service/report.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss'],
})
export class ReportsComponent implements OnInit {
  susPatients!: Array<Patient>;

  productReport!: ProductReport;

  constructor(private reportControler: ReportService) {}

  ngOnInit(): void {
    this.reportControler.getProductReport().subscribe({
      next: (success) => {
        this.productReport = success.body as ProductReport;
      },
      error: (error) => {
        // TODO toastr
        console.log(error);
      },
    });

    this.reportControler.getSusPatients().subscribe({
      next: (success) => {
        this.susPatients = success.body as Array<Patient>;
      },
      error: (error) => {
        // TODO toastr
        console.log(error);
      },
    });
  }
}
