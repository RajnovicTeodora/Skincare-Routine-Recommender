import { Product } from './product';

export interface ProductWithIngredients extends Product {
  ingredients: Array<String>;
}
