package sbnz.skincare.dto;

public class NewProductReactionDTO {

    private long productId;

    private String username;

    private String symptom;

    public NewProductReactionDTO() {
        super();
    }

    public NewProductReactionDTO(long productId, String username, String symptom) {
        this.productId = productId;
        this.username = username;
        this.symptom = symptom;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
