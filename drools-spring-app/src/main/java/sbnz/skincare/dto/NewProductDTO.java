package sbnz.skincare.dto;

import sbnz.skincare.facts.enumerations.ProductType;

import javax.validation.constraints.*;
import java.util.List;

public class NewProductDTO {

    @NotBlank
    @Size(min = 2, max = 15)
    @Pattern(message = "Name can contain alphanumeric characters only", regexp = "[a-zA-Z0-9]+")
    private String name;

    @Pattern(regexp = "CLEANSER|EXFOLIATOR|SERUM|SUN_SCREEN|MOISTURIZER")
    private ProductType productType;

    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(message = "Manufacturer can contain alphanumeric characters only", regexp = "[a-zA-Z0-9]+")
    private String manufacturer;

    @NotBlank
    @Size(min = 2, max = 120)
    @Pattern(message = "Usage Instructions can contain dots and alphanumeric characters only", regexp = "[a-zA-Z0-9.]+")
    private String usageInstructions;

    @NotBlank
    private String image;

    @DecimalMax("999.99")
    @DecimalMin("0.1")
    private double price;

    @NotEmpty
    private List<Long> ingredients;

    public NewProductDTO() {
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

    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }
}
