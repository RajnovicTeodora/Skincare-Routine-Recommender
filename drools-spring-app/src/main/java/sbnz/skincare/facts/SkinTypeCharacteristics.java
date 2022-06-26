package sbnz.skincare.facts;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import sbnz.skincare.facts.enumerations.SkinCharacteristic;
import sbnz.skincare.facts.enumerations.SkinType;

@Entity
@Table(name = "skin_type_characteristics")
public class SkinTypeCharacteristics implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "skin_type")
    @Enumerated(EnumType.STRING)
    private SkinType skinType;

    @ElementCollection(targetClass = SkinCharacteristic.class)
    @JoinTable(name = "skin_characteristics", joinColumns = @JoinColumn(name = "skin_type_id"))
    @Column(name = "characteristic", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<SkinCharacteristic> skinCharacteristics;

    public SkinTypeCharacteristics() {
        super();
    }

    public SkinTypeCharacteristics(SkinType skinType, List<SkinCharacteristic> skinCharacteristics) {
        super();
        this.skinType = skinType;
        this.skinCharacteristics = skinCharacteristics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    public List<SkinCharacteristic> getSkinCharacteristics() {
        return skinCharacteristics;
    }

    public void setSkinCharacteristics(List<SkinCharacteristic> skinCharacteristics) {
        this.skinCharacteristics = skinCharacteristics;
    }

}
