package sbnz.skincare.facts.drools;

import java.io.Serializable;

import sbnz.skincare.facts.enumerations.ProductType;

public class ProductScoring implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public long productId;

    public double score;

    public ProductType productType;

    public ProductScoring() {
        super();
    }

    public ProductScoring(long productId, double score, ProductType productType) {
        super();
        this.productId = productId;
        this.score = score;
        this.productType = productType;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

}
