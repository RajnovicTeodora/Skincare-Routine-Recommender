package sbnz.skincare.facts.drools;

import java.io.Serializable;

public class IngredientScoring implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String ingredient;

	private double score;

	public IngredientScoring() {
		super();
	}

	public IngredientScoring(String ingredient, double score) {
		super();
		this.ingredient = ingredient;
		this.score = score;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
