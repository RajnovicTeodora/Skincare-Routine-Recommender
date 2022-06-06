package sbnz.skincare.facts.drools;

import java.io.Serializable;

import sbnz.skincare.facts.enumerations.ProductType;

public class ProductScoring implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String name;

	public double score;

	public ProductType productType;

	public ProductScoring() {
		super();
	}

	public ProductScoring(String name, double score, ProductType productType) {
		super();
		this.name = name;
		this.score = score;
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
