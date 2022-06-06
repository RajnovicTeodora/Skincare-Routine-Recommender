package sbnz.skincare.facts;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sbnz.skincare.facts.enumerations.AcneType;
import sbnz.skincare.facts.enumerations.AgeGroup;
import sbnz.skincare.facts.enumerations.SkinType;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "benefiting_acne", nullable = true)
	private AcneType benefitingAcne;

	@Column(name = "benefiting_age", nullable = true)
	private AgeGroup benefitingAge;

	@Column(name = "benefiting_skintype", nullable = true)
	private SkinType benefitingSkintype;

	@ManyToMany
	@JoinTable(name = "ingredient_goal", joinColumns = {
			@JoinColumn(name = "ingredient_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "goal_id", referencedColumnName = "id") })
	@JsonIgnore
	private List<Goal> benefitingGoals;

	public Ingredient() {
		super();
	}

	public Ingredient(String name, AcneType benefitingAcne, AgeGroup benefitingAge, SkinType benefitingSkintype,
			List<Goal> benefitingGoals) {
		super();
		this.name = name;
		this.benefitingAcne = benefitingAcne;
		this.benefitingAge = benefitingAge;
		this.benefitingSkintype = benefitingSkintype;
		this.benefitingGoals = benefitingGoals;
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

	public AcneType getBenefitingAcne() {
		return benefitingAcne;
	}

	public void setBenefitingAcne(AcneType benefitingAcne) {
		this.benefitingAcne = benefitingAcne;
	}

	public AgeGroup getBenefitingAge() {
		return benefitingAge;
	}

	public void setBenefitingAge(AgeGroup benefitingAge) {
		this.benefitingAge = benefitingAge;
	}

	public SkinType getBenefitingSkintype() {
		return benefitingSkintype;
	}

	public void setBenefitingSkintype(SkinType benefitingSkintype) {
		this.benefitingSkintype = benefitingSkintype;
	}

	public List<Goal> getBenefitingGoals() {
		return benefitingGoals;
	}

	public void setBenefitingGoals(List<Goal> benefitingGoals) {
		this.benefitingGoals = benefitingGoals;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
