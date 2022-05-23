package sbnz.skincare.facts;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.skincare.facts.enumerations.ProductType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "product_type", nullable = false)
	private ProductType productType;

	@Column(name = "manufacturer", nullable = false)
	private String manufacturer;

	@Column(name = "image", nullable = false, columnDefinition = "text", length = 10485760)
	private String image;

	@Column(name = "price", nullable = false)
	private double price;

	@ManyToMany
	@JoinTable(name = "product_ingredient", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "ingredient_id", referencedColumnName = "id") })
	@JsonIgnore
	private List<Ingredient> ingredients;

}
