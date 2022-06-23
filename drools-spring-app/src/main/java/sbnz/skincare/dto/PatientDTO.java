package sbnz.skincare.dto;

import sbnz.skincare.facts.Patient;
import sbnz.skincare.facts.enumerations.Gender;

public class PatientDTO extends UserDTO {

    private String birthday;

    private Gender gender;

    public PatientDTO() {
    }

    public PatientDTO(String username, String name, String surname, String email, String birthday, Gender gender) {
        super(username, name, surname, email, "PATIENT");
        this.birthday = birthday;
        this.gender = gender;
    }

    public PatientDTO(Patient patient) {
        super(patient.getUsername(), patient.getName(), patient.getSurname(), patient.getEmail(), "PATIENT");
        this.birthday = patient.getBirthday().toString();
        this.gender = patient.getGender();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
