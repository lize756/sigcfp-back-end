package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CURRICULUM database table.
 */
@Entity
@Table(name = "CURRICULUM")
@NamedQuery(name = "Curriculum.findAll", query = "SELECT c FROM Curriculum c")
public class Curriculum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURR_ID", unique = true, nullable = false, precision = 10)
    private long currId;

    @Column(name = "CURR_CITY", nullable = false, length = 255)
    private String currCity;

    @Temporal(TemporalType.DATE)
    @Column(name = "CURR_CREATE_DATE", nullable = false)
    private Date currCreateDate;

    @Column(name = "CURR_EXPERIENCE", nullable = false, length = 255)
    private String currExperience;

    @Column(name = "CURR_SALARY", nullable = false, precision = 9)
    private BigDecimal currSalary;

    //bi-directional many-to-many association to Career
    @ManyToMany
    @JoinTable(
            name = "CURR_CAREER"
            , joinColumns = {
            @JoinColumn(name = "CURRICULUM_CURR_ID", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "CAREER_CARE_ID", nullable = false)
    }
    )
    private List<Career> careers;

    //bi-directional many-to-one association to Company
    @ManyToOne
    @JoinColumn(name = "COMPANY_COMP_ID", nullable = false)
    private Company company;

    //bi-directional many-to-one association to CurriculumPdf
    @ManyToOne
    @JoinColumn(name = "CURRICULUM_PDF_CU_PDF_ID", nullable = false)
    private CurriculumPdf curriculumPdf;

    //bi-directional many-to-one association to Person
    @ManyToOne
    @JoinColumn(name = "PERSON_PERS_ID", nullable = false)
    private Person person;

    //bi-directional many-to-one association to CurriculumPdf
    @OneToMany(mappedBy = "curriculum")
    private List<CurriculumPdf> curriculumPdfs;

    //bi-directional many-to-one association to Skill
    @OneToMany(mappedBy = "curriculum")
    private List<Skill> skills;

    public Curriculum() {
    }

    public long getCurrId() {
        return this.currId;
    }

    public void setCurrId(long currId) {
        this.currId = currId;
    }

    public String getCurrCity() {
        return this.currCity;
    }

    public void setCurrCity(String currCity) {
        this.currCity = currCity;
    }

    public Date getCurrCreateDate() {
        return this.currCreateDate;
    }

    public void setCurrCreateDate(Date currCreateDate) {
        this.currCreateDate = currCreateDate;
    }

    public String getCurrExperience() {
        return this.currExperience;
    }

    public void setCurrExperience(String currExperience) {
        this.currExperience = currExperience;
    }

    public BigDecimal getCurrSalary() {
        return this.currSalary;
    }

    public void setCurrSalary(BigDecimal currSalary) {
        this.currSalary = currSalary;
    }

    public List<Career> getCareers() {
        return this.careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CurriculumPdf getCurriculumPdf() {
        return this.curriculumPdf;
    }

    public void setCurriculumPdf(CurriculumPdf curriculumPdf) {
        this.curriculumPdf = curriculumPdf;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<CurriculumPdf> getCurriculumPdfs() {
        return this.curriculumPdfs;
    }

    public void setCurriculumPdfs(List<CurriculumPdf> curriculumPdfs) {
        this.curriculumPdfs = curriculumPdfs;
    }

    public CurriculumPdf addCurriculumPdf(CurriculumPdf curriculumPdf) {
        getCurriculumPdfs().add(curriculumPdf);
        curriculumPdf.setCurriculum(this);

        return curriculumPdf;
    }

    public CurriculumPdf removeCurriculumPdf(CurriculumPdf curriculumPdf) {
        getCurriculumPdfs().remove(curriculumPdf);
        curriculumPdf.setCurriculum(null);

        return curriculumPdf;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Skill addSkill(Skill skill) {
        getSkills().add(skill);
        skill.setCurriculum(this);

        return skill;
    }

    public Skill removeSkill(Skill skill) {
        getSkills().remove(skill);
        skill.setCurriculum(null);

        return skill;
    }

}
