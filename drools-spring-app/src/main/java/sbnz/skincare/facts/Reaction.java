package sbnz.skincare.facts;

import java.io.Serializable;

import org.kie.api.definition.type.Position;

public class Reaction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Position(0)
    private String reaction;

    @Position(1)
    private String symptom;

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

        if (symptom != null ? !symptom.equals(reaction1.symptom) : reaction1.symptom != null) {
            return false;
        }
        if (reaction != null ? !reaction.equals(reaction1.reaction) : reaction1.reaction != null) {
            return false;
        }

        return true;
    }
}
