package sbnz.skincare.dto;

import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.enumerations.ProductType;

public class ProductDTO {

    private String name;
    private ProductType productType;
    private String manufacturer;
    private String usageInstructions;
    private String image;
    private double price;

    // TODO - maybe add ingredients as a string


    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.productType = product.getProductType();
        this.manufacturer = product.getManufacturer();
        this.usageInstructions = product.getUsageInstructions();
        this.image = product.getImage();
        this.price = product.getPrice();
    }

    public ProductDTO(String name, ProductType productType, String manufacturer, String usageInstructions, String image, double price) {
        this.name = name;
        this.productType = productType;
        this.manufacturer = manufacturer;
        this.usageInstructions = usageInstructions;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUsageInstructions() {
        return usageInstructions;
    }

    public void setUsageInstructions(String usageInstructions) {
        this.usageInstructions = usageInstructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
