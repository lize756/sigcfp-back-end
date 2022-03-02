package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CURRICULUM database table.
 * 
 */
@Entity
@Table(name="CURRICULUM")
@NamedQuery(name="Curriculum.findAll", query="SELECT c FROM Curriculum c")
public class Curriculum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CURRICULUM_CURRID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURRICULUM_CURRID_GENERATOR")
	@Column(name="CURR_ID", unique=true, nullable=false, precision=10)
	private long currId;

	@Temporal(TemporalType.DATE)
	@Column(name="CURR_CREATE_DATE", nullable=false)
	private Date currCreateDate;

	@Column(name="CURR_EXPERIENCE", nullable=false, precision=3)
	private BigDecimal currExperience;

	@Column(name="CURR_SALARY", nullable=false, precision=9)
	private BigDecimal currSalary;

	//bi-directional many-to-one association to Academicstudy
	@OneToMany(mappedBy="curriculum")
	private List<Academicstudy> academicstudies;

	//bi-directional many-to-many association to Career
	@ManyToMany
	@JoinTable(
		name="CURR_CARE"
		, joinColumns={
			@JoinColumn(name="CURRICULUM_CURR_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="CAREER_CARE_ID", nullable=false)
			}
		)
	private List<Career> careers;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY_COMP_ID")
	private Company company;

	//bi-directional many-to-one association to CurriculumPdf
	@ManyToOne
	@JoinColumn(name="CURRICULUM_PDF_CU_PDF_ID")
	private CurriculumPdf curriculumPdf;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSON_PERS_ID")
	private Person person;

	//bi-directional many-to-one association to CurriculumPdf
	@OneToMany(mappedBy="curriculum")
	private List<CurriculumPdf> curriculumPdfs;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="curriculum")
	private List<Person> persons;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="curriculum")
	private List<Skill> skills;

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

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setCurriculum(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setCurriculum(null);

		return person;
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
