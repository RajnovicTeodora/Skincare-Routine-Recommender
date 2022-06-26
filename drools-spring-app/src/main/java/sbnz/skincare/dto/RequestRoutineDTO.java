package sbnz.skincare.dto;

import sbnz.skincare.facts.enumerations.AcneType;
import sbnz.skincare.facts.enumerations.Goal;
import sbnz.skincare.facts.enumerations.SkinCharacteristic;

import java.util.List;

public class RequestRoutineDTO {

    private String patientUsername;

    private List<SkinCharacteristic> skinCharacteristics;

    private List<Goal> wantedGoals;

    private AcneType acneType;

    private List<String> allergies;

    private String manufacturer;

    public RequestRoutineDTO() {
        super();
    }

    public RequestRoutineDTO(String patientUsername, List<SkinCharacteristic> skinCharacteristics, List<Goal> wantedGoals, AcneType acneType, List<String> allergies, String manufacturer) {
        this.patientUsername = patientUsername;
        this.skinCharacteristics = skinCharacteristics;
        this.wantedGoals = wantedGoals;
        this.acneType = acneType;
        this.allergies = allergies;
        this.manufacturer = manufacturer;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public List<SkinCharacteristic> getSkinCharacteristics() {
        return skinCharacteristics;
    }

    public void setSkinCharacteristics(List<SkinCharacteristic> skinCharacteristics) {
        this.skinCharacteristics = skinCharacteristics;
    }

    public List<Goal> getWantedGoals() {
        return wantedGoals;
    }

    public void setWantedGoals(List<Goal> wantedGoals) {
        this.wantedGoals = wantedGoals;
    }

    public AcneType getAcneType() {
        return acneType;
    }

    public void setAcneType(AcneType acneType) {
        this.acneType = acneType;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
