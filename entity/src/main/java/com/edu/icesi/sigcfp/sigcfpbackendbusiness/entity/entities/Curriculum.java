package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Curriculum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CURRICULUM_CURRID_GENERATOR", allocationSize = 1, sequenceName = "CURRICULUM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRICULUM_CURRID_GENERATOR")
    @Column(name = "CURR_ID", unique = true, nullable = false, precision = 10)
    private long currId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CURR_CREATE_DATE", nullable = true)
    private Date currCreateDate;

    @Column(name = "CURR_EXPERIENCE", precision = 3, nullable = true)
    private BigDecimal currExperience;

	@Column(name = "CURR_IS_LABOR_MOBILITY", length = 5, nullable = true)
    private String currIsLaborMobility;
    
    
    @Column(name = "CURR_SALARY", precision = 9, nullable = true)
    private BigDecimal currSalary;
    
    @Column(name="CURR_DESCRIPTION", length = 2000, nullable = true)
    private String currDescription;
    

    //bi-directional many-to-one association to Academicstudy
    @OneToMany(mappedBy = "curriculum")
    @JsonIgnore
    private List<Academicstudy> academicstudies;

    //bi-directional many-to-many association to Career
    @ManyToMany
    @JoinTable(
            name = "CURR_CARE"
            , joinColumns = {
            @JoinColumn(name = "CURRICULUM_CURR_ID", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "CAREER_CARE_ID", nullable = false)
    }
    )
    @JsonIgnore
    private List<Career> careers;

    //bi-directional many-to-one association to Company
    @ManyToOne
    @JoinColumn(name = "COMPANY_COMP_ID", nullable = true)
    @JsonIgnore
    private Company company;

    //bi-directional many-to-one association to CurriculumPdf
    @ManyToOne
    @JoinColumn(name = "CURRICULUM_PDF_CU_PDF_ID", nullable = true)
    @JsonIgnore
    private CurriculumPdf curriculumPdf;

    //bi-directional many-to-one association to Person
    @ManyToOne
    @JoinColumn(name = "PERSON_PERS_ID", nullable = true)
    @JsonIgnore
    private Person person;

    //bi-directional many-to-one association to CurriculumPdf
    @OneToMany(mappedBy = "curriculum")
    @JsonIgnore
    private List<CurriculumPdf> curriculumPdfs;

    //bi-directional many-to-one association to Person
    @OneToMany(mappedBy = "curriculum")
    @JsonIgnore
    private List<Person> persons;

    public Curriculum() {
    }

    public long getCurrId() {
        return this.currId;
    }

    public void setCurrId(long currId) {
        this.currId = currId;
    }

    public Date getCurrCreateDate() {
        return this.currCreateDate;
    }

    public void setCurrCreateDate(Date currCreateDate) {
        this.currCreateDate = currCreateDate;
    }

    public BigDecimal getCurrExperience() {
        return this.currExperience;
    }

    public void setCurrExperience(BigDecimal currExperience) {
        this.currExperience = currExperience;
    }

    public BigDecimal getCurrSalary() {
        return this.currSalary;
    }

    public void setCurrSalary(BigDecimal currSalary) {
        this.currSalary = currSalary;
    }

    public List<Academicstudy> getAcademicstudies() {
        return this.academicstudies;
    }

    public void setAcademicstudies(List<Academicstudy> academicstudies) {
        this.academicstudies = academicstudies;
    }

    public Academicstudy addAcademicstudy(Academicstudy academicstudy) {
        getAcademicstudies().add(academicstudy);
        academicstudy.setCurriculum(this);

        return academicstudy;
    }

    public Academicstudy removeAcademicstudy(Academicstudy academicstudy) {
        getAcademicstudies().remove(academicstudy);
        academicstudy.setCurriculum(null);

        return academicstudy;
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

    public String getCurrDescription() {
		return currDescription;
	}

	public void setCurrDescription(String currDescription) {
		this.currDescription = currDescription;
	}

	public String getCurrIsLaborMobility() {
		return currIsLaborMobility;
	}

	public void setCurrIsLaborMobility(String currIsLaborMobility) {
		this.currIsLaborMobility = currIsLaborMobility;
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

    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }



}
