package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the CAREER database table.
 */
@Entity
@Table(name = "CAREER")
@NamedQuery(name = "Career.findAll", query = "SELECT c FROM Career c")
public class Career implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARE_ID", unique = true, nullable = false, precision = 10)
    private long careId;

    @Column(name = "CARE_FACULTY", length = 255)
    private String careFaculty;

    @Column(name = "CARE_NAME", nullable = false, length = 255)
    private String careName;

    //bi-directional many-to-one association to InternRequest
    @ManyToOne
    @JoinColumn(name = "INTERN_REQUEST_INTE_REQU_ID")
    private InternRequest internRequest;

    //bi-directional many-to-many association to Curriculum
    @ManyToMany(mappedBy = "careers")
    private List<Curriculum> curriculums;

    public Career() {
    }

    public long getCareId() {
        return this.careId;
    }

    public void setCareId(long careId) {
        this.careId = careId;
    }

    public String getCareFaculty() {
        return this.careFaculty;
    }

    public void setCareFaculty(String careFaculty) {
        this.careFaculty = careFaculty;
    }

    public String getCareName() {
        return this.careName;
    }

    public void setCareName(String careName) {
        this.careName = careName;
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

}
