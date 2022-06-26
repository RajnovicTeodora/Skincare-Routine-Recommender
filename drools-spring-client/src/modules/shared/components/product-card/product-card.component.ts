import { Component, Input, OnInit, Output } from '@angular/core';
import { ProductWithIngredients } from 'src/modules/shared/models/product-with-ingredients';
import { HelperService } from 'src/modules/shared/services/helper-service/helper.service';
import { EventEmitter } from '@angular/core';
import { ReactionService } from '../../services/reaction-service/reaction.service';
import { MatDialog } from '@angular/material/dialog';
import { ReactionDialogComponent } from '../reaction-dialog/reaction-dialog.component';
import { CheckReaction } from '../../models/check-reaction';
import { Observable } from 'rxjs';

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

  @Input()
  username!: String;

  hasReaction: Observable<Boolean>;

  @Output() deleteClicked = new EventEmitter();

  constructor(
    private helperService: HelperService,
    private reactionService: ReactionService,
    public dialog: MatDialog
  ) {
    this.hasReaction = new Observable((observer) => observer.next(false));
  }

  ngOnInit() {
    this.reactionService.hasReaction(this.username, this.product.id).subscribe({
      next: (result) =>
        (this.hasReaction = new Observable((observer) =>
          observer.next(result.body)
        )),
    });
  }

  deleteItem(id: Number) {
    this.deleteClicked.emit(id);
  }

  bypassUrlSecurity(image: String) {
    return this.helperService.bypassUrlSecurity(image);
  }

  addReaction(): void {
    const dialogRef = this.dialog.open(ReactionDialogComponent, {
      width: '30%',
      data: '',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result.data == null) return;
      const reactionInput: CheckReaction = {
        productId: this.product.id,
        username: this.username,
        symptom: result.data,
      };
      console.log(reactionInput);
      this.reactionService.checkReaction(reactionInput).subscribe({
        next: (result) => {
          if (result.body == null) {
            // TODO toastr
          } else {
            this.hasReaction = new Observable((observer) =>
              observer.next(true)
            );
          }
        },
      });
    });
  }
}
