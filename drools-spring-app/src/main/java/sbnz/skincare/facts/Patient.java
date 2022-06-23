package sbnz.skincare.facts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sbnz.skincare.dto.NewPatientDTO;
import sbnz.skincare.facts.enumerations.Gender;

@Entity
public class Patient extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    protected List<Routine> routines = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    protected List<ProductReaction> productReactions = new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(NewPatientDTO dto, UserRole role) {
        super(dto, role);
        this.birthday = dto.getBirthday();
        this.gender = dto.getGender();
        this.productReactions = new ArrayList<>();
        this.routines = new ArrayList<>();
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

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    public List<ProductReaction> getProductReactions() {
        return productReactions;
    }

    public void setProductReactions(List<ProductReaction> productReactions) {
        this.productReactions = productReactions;
    }

}
