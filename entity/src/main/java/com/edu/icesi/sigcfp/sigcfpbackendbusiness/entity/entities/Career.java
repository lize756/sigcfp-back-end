package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the CAREER database table.
 */
@Entity
@Table(name = "CAREER")
@NamedQuery(name = "Career.findAll", query = "SELECT c FROM Career c")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Career implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CAREER_CAREID_GENERATOR", allocationSize = 1, sequenceName = "CAREER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAREER_CAREID_GENERATOR")
    @Column(name = "CARE_ID", unique = true, nullable = false, precision = 10)
    private long careId;

    @Column(name = "CARE_DESCRIPTION", length = 1000)
    private String careDescription;

    @Column(name = "CARE_NAME", length = 255)
    private String careName;

    @ManyToOne
    @JoinColumn(name = "PERSON_PERS_ID")
    private Person person;

    //bi-directional many-to-one association to Faculty
    @ManyToOne
    @JoinColumn(name = "FACULTY_FACU_ID")
    //@JsonIgnore
    private Faculty faculty;

    //bi-directional many-to-one association to InternRequest
    @ManyToOne
    @JoinColumn(name = "INTERN_REQUEST_INTE_REQU_ID")
    @JsonIgnore
    private InternRequest internRequest;

    //bi-directional many-to-many association to Curriculum
    @ManyToMany(mappedBy = "careers")
    @JsonIgnore
    private List<Curriculum> curriculums;

    //bi-directional many-to-many association to InternRequest
    @ManyToMany(mappedBy = "careers")
    @JsonIgnore
    private List<InternRequest> internRequests;

    public Career() {
    }

    public long getCareId() {
        return this.careId;
    }

    public void setCareId(long careId) {
        this.careId = careId;
    }

    public String getCareDescription() {
        return this.careDescription;
    }

    public void setCareDescription(String careDescription) {
        this.careDescription = careDescription;
    }

    public String getCareName() {
        return this.careName;
    }

    public void setCareName(String careName) {
        this.careName = careName;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public InternRequest getInternRequest() {
        return this.internRequest;
    }

    public void setInternRequest(InternRequest internRequest) {
        this.internRequest = internRequest;
    }

    public List<Curriculum> getCurriculums() {
        return this.curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public List<InternRequest> getInternRequests() {
        return this.internRequests;
    }

    public void setInternRequests(List<InternRequest> internRequests) {
        this.internRequests = internRequests;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
