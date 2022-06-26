package sbnz.skincare.facts;

import java.io.Serializable;
import java.util.Objects;

import org.kie.api.definition.type.Position;
import javax.persistence.*;

@Entity
@Table(name = "reaction")
public class Reaction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Position(0)
    @Column(name = "reaction", nullable = false)
    private String reaction;

    @Position(1)
    @Column(name = "symptom", nullable = false)
    private String symptom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reaction() {
        super();
    }

    public Reaction(String reaction, String symptom) {
        super();
        this.reaction = reaction;
        this.symptom = symptom;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reaction reaction1 = (Reaction) o;

        if (!Objects.equals(symptom, reaction1.symptom)) {
            return false;
        }
        return Objects.equals(reaction, reaction1.reaction);
    }
}
