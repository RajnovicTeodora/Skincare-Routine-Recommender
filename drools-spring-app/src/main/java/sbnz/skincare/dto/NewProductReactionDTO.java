package sbnz.skincare.dto;

public class NewProductReactionDTO {

	private long productId;

	// TODO user

	private Boolean skinRedness;

	private Boolean itching;

	private Boolean redPatches;

	private Boolean swelling;

	private Boolean lowBloodPressure;

	private int heartRate;

	private Boolean breakouts;

	private int monthsOfProductUse;

	public NewProductReactionDTO() {
		super();
	}

	public NewProductReactionDTO(long productId, Boolean skinRedness, Boolean itching, Boolean redPatches,
			Boolean swelling, Boolean lowBloodPressure, int heartRate, Boolean breakouts, int monthsOfProductUse) {
		super();
		this.productId = productId;
		this.skinRedness = skinRedness;
		this.itching = itching;
		this.redPatches = redPatches;
		this.swelling = swelling;
		this.lowBloodPressure = lowBloodPressure;
		this.heartRate = heartRate;
		this.breakouts = breakouts;
		this.monthsOfProductUse = monthsOfProductUse;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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
