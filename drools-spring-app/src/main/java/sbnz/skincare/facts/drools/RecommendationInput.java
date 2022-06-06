package sbnz.skincare.facts.drools;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import sbnz.skincare.facts.enumerations.AcneType;
import sbnz.skincare.facts.enumerations.AgeGroup;
import sbnz.skincare.facts.enumerations.SkinCharacteristic;
import sbnz.skincare.facts.enumerations.SkinType;

public class RecommendationInput implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate lastRoutineDate; // TODO
	private List<SkinCharacteristic> skinCharacteristics;
	private SkinType diagnosedSkinType;
	private LocalDate birthday;
	private AgeGroup ageGroup;
	private List<String> wantedGoals;
	private AcneType acneType;
	private List<String> alergies;
	private String manufacturer;

	public RecommendationInput() {
		super();
	}

	public RecommendationInput(LocalDate lastRoutineDate, List<SkinCharacteristic> skinCharacteristics,
			SkinType diagnosedSkinType, LocalDate birthday, AgeGroup ageGroup, List<String> wantedGoals,
			AcneType acneType, List<String> alergies, String manufacturer) {
		super();
		this.lastRoutineDate = lastRoutineDate;
		this.skinCharacteristics = skinCharacteristics;
		this.diagnosedSkinType = diagnosedSkinType;
		this.birthday = birthday;
		this.ageGroup = ageGroup;
		this.wantedGoals = wantedGoals;
		this.acneType = acneType;
		this.alergies = alergies;
		this.manufacturer = manufacturer;
	}

	public LocalDate getLastRoutineDate() {
		return lastRoutineDate;
	}

	public void setLastRoutineDate(LocalDate lastRoutineDate) {
		this.lastRoutineDate = lastRoutineDate;
	}

	public List<SkinCharacteristic> getSkinCharacteristics() {
		return skinCharacteristics;
	}

	public void setSkinCharacteristics(List<SkinCharacteristic> skinCharacteristics) {
		this.skinCharacteristics = skinCharacteristics;
	}

	public SkinType getDiagnosedSkinType() {
		return diagnosedSkinType;
	}

	public void setDiagnosedSkinType(SkinType diagnosedSkinType) {
		this.diagnosedSkinType = diagnosedSkinType;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public List<String> getWantedGoals() {
		return wantedGoals;
	}

	public void setWantedGoals(List<String> wantedGoals) {
		this.wantedGoals = wantedGoals;
	}

	public AcneType getAcneType() {
		return acneType;
	}

	public void setAcneType(AcneType acneType) {
		this.acneType = acneType;
	}

	public List<String> getAlergies() {
		return alergies;
	}

	public void setAlergies(List<String> alergies) {
		this.alergies = alergies;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
