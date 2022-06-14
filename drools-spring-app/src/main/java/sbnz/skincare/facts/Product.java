package sbnz.skincare.facts;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sbnz.skincare.facts.enumerations.ProductType;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "usage_instructions", nullable = false)
    private String usageInstructions;

    @Column(name = "image", nullable = false, columnDefinition = "text", length = 10485760)
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany
    @JoinTable(name = "product_ingredient", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "ingredient_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Ingredient> ingredients;

    public Product() {
        super();
    }

    public Product(String name, ProductType productType, String manufacturer, String usageInstructions, String image, double price,
                   List<Ingredient> ingredients) {
        super();
        this.name = name;
        this.productType = productType;
        this.manufacturer = manufacturer;
        this.usageInstructions = usageInstructions;
        this.image = image;
        this.price = price;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
