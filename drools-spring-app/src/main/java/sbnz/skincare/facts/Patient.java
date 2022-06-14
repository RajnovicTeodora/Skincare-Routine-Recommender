package sbnz.skincare.facts;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    protected List<Routine> routines;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    protected List<ProductReaction> productReactions;

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
