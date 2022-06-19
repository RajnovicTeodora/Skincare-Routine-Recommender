package sbnz.skincare.facts;

import sbnz.skincare.dto.NewUserDTO;

import javax.persistence.Entity;

@Entity
public class Dermatologist extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Dermatologist() {
        super();
    }

    public Dermatologist(NewUserDTO dto, UserRole role) {
        super(dto, role);
    }
}
