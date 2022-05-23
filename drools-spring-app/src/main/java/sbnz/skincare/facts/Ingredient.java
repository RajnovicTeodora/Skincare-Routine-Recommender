package sbnz.skincare.facts;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.skincare.facts.enumerations.AcneType;
import sbnz.skincare.facts.enumerations.AgeGroup;
import sbnz.skincare.facts.enumerations.SkinType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {

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

}
