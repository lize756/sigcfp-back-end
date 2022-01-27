package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 */
@Entity
@Table(name = "COMPANY")
@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMP_ID", unique = true, nullable = false, precision = 10)
    private long compId;

    @Column(name = "COMP_CITY", nullable = false, length = 255)
    private String compCity;

    @Column(name = "COMP_EMAIL", nullable = false, length = 100)
    private String compEmail;

    @Column(name = "COMP_NAME", nullable = false, length = 255)
    private String compName;

    @Column(name = "COMP_NIT", nullable = false, length = 255)
    private String compNit;

    @Column(name = "COMP_SECTOR", nullable = false, length = 255)
    private String compSector;

    @Column(name = "COMP_TELEPHONE", nullable = false, length = 20)
    private String compTelephone;

    @Column(name = "COMP_TYPE", nullable = false, length = 20)
    private String compType;

    //bi-directional many-to-one association to Curriculum
    @OneToMany(mappedBy = "company")
    private List<Curriculum> curriculums;

    //bi-directional many-to-one association to InternRequest
    @OneToMany(mappedBy = "company")
    private List<InternRequest> internRequests;

    //bi-directional many-to-many association to Noti
    @ManyToMany(mappedBy = "companies")
    private List<Noti> notis;

    //bi-directional many-to-one association to Userr
    @OneToMany(mappedBy = "company")
    private List<Userr> userrs;

    public Company() {
    }

    public long getCompId() {
        return this.compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getCompCity() {
        return this.compCity;
    }

    public void setCompCity(String compCity) {
        this.compCity = compCity;
    }

    public String getCompEmail() {
        return this.compEmail;
    }

    public void setCompEmail(String compEmail) {
        this.compEmail = compEmail;
    }

    public String getCompName() {
        return this.compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompNit() {
        return this.compNit;
    }

    public void setCompNit(String compNit) {
        this.compNit = compNit;
    }

    public String getCompSector() {
        return this.compSector;
    }

    public void setCompSector(String compSector) {
        this.compSector = compSector;
    }

    public String getCompTelephone() {
        return this.compTelephone;
    }

    public void setCompTelephone(String compTelephone) {
        this.compTelephone = compTelephone;
    }

    public String getCompType() {
        return this.compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public List<Curriculum> getCurriculums() {
        return this.curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public Curriculum addCurriculum(Curriculum curriculum) {
        getCurriculums().add(curriculum);
        curriculum.setCompany(this);

        return curriculum;
    }

    public Curriculum removeCurriculum(Curriculum curriculum) {
        getCurriculums().remove(curriculum);
        curriculum.setCompany(null);

        return curriculum;
    }

    public List<InternRequest> getInternRequests() {
        return this.internRequests;
    }

    public void setInternRequests(List<InternRequest> internRequests) {
        this.internRequests = internRequests;
    }

    public InternRequest addInternRequest(InternRequest internRequest) {
        getInternRequests().add(internRequest);
        internRequest.setCompany(this);

        return internRequest;
    }

    public InternRequest removeInternRequest(InternRequest internRequest) {
        getInternRequests().remove(internRequest);
        internRequest.setCompany(null);

        return internRequest;
    }

    public List<Noti> getNotis() {
        return this.notis;
    }

    public void setNotis(List<Noti> notis) {
        this.notis = notis;
    }

    public List<Userr> getUserrs() {
        return this.userrs;
    }

    public void setUserrs(List<Userr> userrs) {
        this.userrs = userrs;
    }

    public Userr addUserr(Userr userr) {
        getUserrs().add(userr);
        userr.setCompany(this);

        return userr;
    }

    public Userr removeUserr(Userr userr) {
        getUserrs().remove(userr);
        userr.setCompany(null);

        return userr;
    }

}
