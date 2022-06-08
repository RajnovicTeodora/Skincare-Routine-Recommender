package sbnz.skincare.dto;

public class NewProductReactionDTO {

    private long productId;

    // TODO user

    private String reaction;

    private String symptom;

    public NewProductReactionDTO() {
        super();
    }

    public NewProductReactionDTO(long productId, String reaction, String symptom) {
        super();
        this.productId = productId;
        this.reaction = reaction;
        this.symptom = symptom;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
