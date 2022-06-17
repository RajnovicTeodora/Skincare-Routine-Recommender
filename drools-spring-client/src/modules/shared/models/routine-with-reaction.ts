import { ProductReaction } from './product-reaction';

export interface RoutineWithReaction {
  startDate: Date;
  productWithReaction: Array<ProductReaction>;
}
