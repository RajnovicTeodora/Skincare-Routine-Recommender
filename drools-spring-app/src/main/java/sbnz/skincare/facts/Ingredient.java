package sbnz.skincare.facts;

import sbnz.skincare.facts.enumerations.AcneType;
import sbnz.skincare.facts.enumerations.AgeGroup;
import sbnz.skincare.facts.enumerations.Goal;
import sbnz.skincare.facts.enumerations.SkinType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "benefiting_acne", nullable = true)
    @Enumerated(EnumType.STRING)
    private AcneType benefitingAcne;

    @Column(name = "benefiting_age", nullable = true)
    @Enumerated(EnumType.STRING)
    private AgeGroup benefitingAge;

    @Column(name = "benefiting_skin_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private SkinType benefitingSkinType;

    @ElementCollection(targetClass = Goal.class)
    @JoinTable(name = "benefiting_goals", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "goal", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Goal> benefitingGoals;

    public Ingredient() {
        super();
    }

    public Ingredient(String name, AcneType benefitingAcne, AgeGroup benefitingAge, SkinType benefitingSkinType,
                      List<Goal> benefitingGoals) {
        super();
        this.name = name;
        this.benefitingAcne = benefitingAcne;
        this.benefitingAge = benefitingAge;
        this.benefitingSkinType = benefitingSkinType;
        this.benefitingGoals = benefitingGoals;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AcneType getBenefitingAcne() {
        return benefitingAcne;
    }

    public void setBenefitingAcne(AcneType benefitingAcne) {
        this.benefitingAcne = benefitingAcne;
    }

    public AgeGroup getBenefitingAge() {
        return benefitingAge;
    }

    public void setBenefitingAge(AgeGroup benefitingAge) {
        this.benefitingAge = benefitingAge;
    }

    public SkinType getBenefitingSkinType() {
        return benefitingSkinType;
    }

    public void setBenefitingSkinType(SkinType benefitingSkinType) {
        this.benefitingSkinType = benefitingSkinType;
    }

    public List<Goal> getBenefitingGoals() {
        return benefitingGoals;
    }

    public void setBenefitingGoals(List<Goal> benefitingGoals) {
        this.benefitingGoals = benefitingGoals;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
