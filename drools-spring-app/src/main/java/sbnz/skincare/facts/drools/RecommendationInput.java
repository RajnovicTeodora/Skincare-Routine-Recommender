package sbnz.skincare.facts.drools;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import sbnz.skincare.dto.RequestRoutineDTO;
import sbnz.skincare.facts.enumerations.*;

public class RecommendationInput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Entered patient's skin characteristics
    private List<SkinCharacteristic> skinCharacteristics;

    // Diagnosed skin typed based on skin characteristics
    private SkinType diagnosedSkinType;

    // Patient's birthday
    private LocalDate birthday;

    // Patient's age group based on date of birth
    private AgeGroup ageGroup;

    // Patient's goals to achieve
    private List<Goal> wantedGoals;

    // Type of acne
    private AcneType acneType;

    // List of allergies
    private List<String> allergies;

    // Previously used product manufacturer
    private String manufacturer;

    public RecommendationInput() {
        super();
    }

    public RecommendationInput(List<SkinCharacteristic> skinCharacteristics, SkinType diagnosedSkinType,
                               LocalDate birthday, AgeGroup ageGroup, List<Goal> wantedGoals,
                               AcneType acneType, List<String> allergies, String manufacturer) {
        super();
        this.skinCharacteristics = skinCharacteristics;
        this.diagnosedSkinType = diagnosedSkinType;
        this.birthday = birthday;
        this.ageGroup = ageGroup;
        this.wantedGoals = wantedGoals;
        this.acneType = acneType;
        this.allergies = allergies;
        this.manufacturer = manufacturer;
    }

    public RecommendationInput(RequestRoutineDTO requestRoutineDTO, LocalDate birthday) {
        super();
        this.skinCharacteristics = requestRoutineDTO.getSkinCharacteristics();
        this.birthday = birthday;
        this.wantedGoals = requestRoutineDTO.getWantedGoals();
        this.acneType = requestRoutineDTO.getAcneType();
        this.allergies = requestRoutineDTO.getAllergies();
        this.manufacturer = requestRoutineDTO.getManufacturer();
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
