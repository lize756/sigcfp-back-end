package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the FACULTY database table.
 */
@Entity
@Table(name = "FACULTY")
@NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "FACULTY_FACUID_GENERATOR", allocationSize = 1, sequenceName = "FACULTY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACULTY_FACUID_GENERATOR")
    @Column(name = "FACU_ID", unique = true, nullable = false, precision = 10)
    private long facuId;

    @Column(name = "FACU_DESCRIPTION", length = 1000)
    private String facuDescription;

    @Column(name = "FACU_NAME", length = 255)
    private String facuName;

    //bi-directional many-to-one association to Career
    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    private List<Career> careers;

    public Faculty() {
    }

    public long getFacuId() {
        return this.facuId;
    }

    public void setFacuId(long facuId) {
        this.facuId = facuId;
    }

    public String getFacuDescription() {
        return this.facuDescription;
    }

    public void setFacuDescription(String facuDescription) {
        this.facuDescription = facuDescription;
    }

    public String getFacuName() {
        return this.facuName;
    }

    public void setFacuName(String facuName) {
        this.facuName = facuName;
    }

    public List<Career> getCareers() {
        return this.careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public Career addCareer(Career career) {
        getCareers().add(career);
        career.setFaculty(this);

        return career;
    }

    public Career removeCareer(Career career) {
        getCareers().remove(career);
        career.setFaculty(null);

        return career;
    }

}
