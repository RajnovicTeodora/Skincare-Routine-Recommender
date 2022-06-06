package sbnz.skincare.facts.drools;

import java.io.Serializable;

import sbnz.skincare.dto.NewProductReactionDTO;

public class ReactionInput implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Boolean skinRedness;

	private Boolean itching;

	private Boolean redPatches;

	private Boolean swelling;

	private Boolean lowBloodPressure;

	private int heartRate;

	private Boolean breakouts;

	private int monthsOfProductUse;

	public ReactionInput() {
		super();
	}

	public ReactionInput(NewProductReactionDTO dto) {
		super();
		this.skinRedness = dto.getSkinRedness();
		this.itching = dto.getItching();
		this.redPatches = dto.getRedPatches();
		this.swelling = dto.getSwelling();
		this.lowBloodPressure = dto.getLowBloodPressure();
		this.heartRate = dto.getHeartRate();
		this.breakouts = dto.getBreakouts();
		this.monthsOfProductUse = dto.getMonthsOfProductUse();
	}

	public ReactionInput(Boolean skinRedness, Boolean itching, Boolean redPatches, Boolean swelling,
			Boolean lowBloodPressure, int heartRate, Boolean breakouts, int monthsOfProductUse) {
		super();
		this.skinRedness = skinRedness;
		this.itching = itching;
		this.redPatches = redPatches;
		this.swelling = swelling;
		this.lowBloodPressure = lowBloodPressure;
		this.heartRate = heartRate;
		this.breakouts = breakouts;
		this.monthsOfProductUse = monthsOfProductUse;
	}

	public Boolean getSkinRedness() {
		return skinRedness;
	}

	public void setSkinRedness(Boolean skinRedness) {
		this.skinRedness = skinRedness;
	}

	public Boolean getItching() {
		return itching;
	}

	public void setItching(Boolean itching) {
		this.itching = itching;
	}

	public Boolean getRedPatches() {
		return redPatches;
	}

	public void setRedPatches(Boolean redPatches) {
		this.redPatches = redPatches;
	}

	public Boolean getSwelling() {
		return swelling;
	}

	public void setSwelling(Boolean swelling) {
		this.swelling = swelling;
	}

	public Boolean getLowBloodPressure() {
		return lowBloodPressure;
	}

	public void setLowBloodPressure(Boolean lowBloodPressure) {
		this.lowBloodPressure = lowBloodPressure;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public Boolean getBreakouts() {
		return breakouts;
	}

	public void setBreakouts(Boolean breakouts) {
		this.breakouts = breakouts;
	}

	public int getMonthsOfProductUse() {
		return monthsOfProductUse;
	}

	public void setMonthsOfProductUse(int monthsOfProductUse) {
		this.monthsOfProductUse = monthsOfProductUse;
	}

}
