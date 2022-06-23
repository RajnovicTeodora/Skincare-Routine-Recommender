package sbnz.skincare.dto;

import sbnz.skincare.facts.drools.ProductReport;

public class ProductReportDTO {

    private ProductDTO bestProduct;

    private ProductDTO worstProduct;

    public ProductReportDTO() {
    }

    public ProductReportDTO(ProductReport report) {
        this.bestProduct = new ProductDTO(report.getBestProduct());
        this.worstProduct = new ProductDTO(report.getWorstProduct());
    }

    public ProductDTO getBestProduct() {
        return bestProduct;
    }

    public void setBestProduct(ProductDTO bestProduct) {
        this.bestProduct = bestProduct;
    }

    public ProductDTO getWorstProduct() {
        return worstProduct;
    }

    public void setWorstProduct(ProductDTO worstProduct) {
        this.worstProduct = worstProduct;
    }
}
