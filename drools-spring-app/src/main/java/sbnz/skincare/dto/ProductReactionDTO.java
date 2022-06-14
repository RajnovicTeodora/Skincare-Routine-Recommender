package sbnz.skincare.dto;

import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.ProductReaction;

public class ProductReactionDTO extends ProductDTO {

    private String reaction;

    public ProductReactionDTO(ProductReaction productReaction) {
        super(productReaction.getProduct());
        this.reaction = productReaction.getReaction();
    }

    public ProductReactionDTO(Product product) {
        super(product);
    }

    public ProductReactionDTO(Product product, String reaction) {
        super(product);
        this.reaction = reaction;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}
