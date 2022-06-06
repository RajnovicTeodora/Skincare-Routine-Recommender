package sbnz.skincare.facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_reaction")
public class ProductReaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "reaction", nullable = false)
	private String reaction;

	@ManyToOne
	private Product product;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	public ProductReaction() {
		super();
	}

	public ProductReaction(String reaction, Product product, Patient patient) {
		super();
		this.reaction = reaction;
		this.product = product;
		this.patient = patient;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
