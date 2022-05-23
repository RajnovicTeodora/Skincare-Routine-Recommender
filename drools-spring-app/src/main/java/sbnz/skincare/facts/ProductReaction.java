package sbnz.skincare.facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_reaction")
public class ProductReaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "reaction", nullable = false)
	private String reaction;

	@Column(name = "advice", nullable = false)
	private String advice;

	@ManyToOne
	private Product product;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

}
