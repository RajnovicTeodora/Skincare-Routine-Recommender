export interface NewProduct {
  name: string;
  productType: string;
  manufacturer: string;
  usageInstructions: string;
  image: string;
  price: number;
  ingredients: Array<number>;
}
