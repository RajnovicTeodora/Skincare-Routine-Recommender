package sbnz.skincare.dto;

import sbnz.skincare.facts.Ingredient;

public class IngredientDTO {

    private long id;
    private String name;

    public IngredientDTO() {
    }

    public IngredientDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
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
}
