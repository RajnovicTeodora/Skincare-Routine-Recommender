package sbnz.skincare.dto;

import sbnz.skincare.facts.enumerations.Gender;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class NewPatientDTO extends NewUserDTO {

    @Past
    @NotNull
    private LocalDate birthday;

    @NotNull
    @Pattern(regexp = "MALE|FEMALE")
    private Gender gender;

    public NewPatientDTO() {
        super();
    }

    public NewPatientDTO(String username, String password, String name, String surname, String email, LocalDate birthday, Gender gender) {
        super(username, password, name, surname, email);
        this.birthday = birthday;
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
