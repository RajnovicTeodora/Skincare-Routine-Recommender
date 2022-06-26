package sbnz.skincare.dto;

import sbnz.skincare.facts.Ingredient;
import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.enumerations.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductWithIngredientsDTO extends ProductDTO {

    private List<String> ingredients = new ArrayList<>();

    public ProductWithIngredientsDTO() {
        super();
    }

    public ProductWithIngredientsDTO(Product product) {
        super(product);
        for (Ingredient i : product.getIngredients()) {
            this.ingredients.add(i.getName());
        }
    }

    public ProductWithIngredientsDTO(String name, ProductType productType, String manufacturer, String usageInstructions, String image, double price, List<String> ingredients) {
        super(name, productType, manufacturer, usageInstructions, image, price);
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
