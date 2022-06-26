package sbnz.skincare.facts.drools;

import java.io.Serializable;

import sbnz.skincare.dto.NewProductReactionDTO;

public class ReactionInput implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String reaction;

	private String symptom;

	public ReactionInput() {
		super();
	}

	public ReactionInput(String reaction, String symptom) {
		super();
		this.reaction = reaction;
		this.symptom = symptom;
	}

	public ReactionInput(NewProductReactionDTO dto) {
		super();
		this.reaction = null;
		this.symptom = dto.getSymptom();
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
