package sbnz.skincare.facts;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}