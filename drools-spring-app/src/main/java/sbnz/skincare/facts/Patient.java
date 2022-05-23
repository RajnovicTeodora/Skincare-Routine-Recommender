package sbnz.skincare.facts;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.skincare.facts.enumerations.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(nullable = false)
	private Gender gender;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	protected List<Routine> routines;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	protected List<ProductReaction> productReactions;

}
