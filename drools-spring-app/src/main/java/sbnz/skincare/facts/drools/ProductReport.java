package sbnz.skincare.facts.drools;

import sbnz.skincare.facts.Product;

public class ProductReport {

    private Product bestProduct;

    private Product worstProduct;

    public ProductReport() {
    }

    public ProductReport(Product bestProduct, Product worstProduct) {
        this.bestProduct = bestProduct;
        this.worstProduct = worstProduct;
    }

    public Product getBestProduct() {
        return bestProduct;
    }

    public void setBestProduct(Product bestProduct) {
        this.bestProduct = bestProduct;
    }

    public Product getWorstProduct() {
        return worstProduct;
    }

    public void setWorstProduct(Product worstProduct) {
        this.worstProduct = worstProduct;
    }
}
