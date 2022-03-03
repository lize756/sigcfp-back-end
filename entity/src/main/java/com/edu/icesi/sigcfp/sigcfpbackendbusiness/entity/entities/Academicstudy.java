package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ACADEMICSTUDY database table.
 * 
 */
@Entity
@Table(name="ACADEMICSTUDY")
@NamedQuery(name="Academicstudy.findAll", query="SELECT a FROM Academicstudy a")
public class Academicstudy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACADEMICSTUDY_ACADSTUDID_GENERATOR" ,sequenceName = "ACADEMY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACADEMICSTUDY_ACADSTUDID_GENERATOR")
	@Column(name="ACAD_STUD_ID", unique=true, nullable=false, precision=10)
	private long acadStudId;

	@Temporal(TemporalType.DATE)
	@Column(name="ACAD_STUD_END_DATE", nullable=false)
	private Date acadStudEndDate;

	@Column(name="ACAD_STUD_INSTI", nullable=false, length=255)
	private String acadStudInsti;

	@Column(name="ACAD_STUD_LEVEL", nullable=false, length=255)
	private String acadStudLevel;

	@Temporal(TemporalType.DATE)
	@Column(name="ACAD_STUD_START_DATE", nullable=false)
	private Date acadStudStartDate;

	@Column(name="ACAD_STUD_STATUS", nullable=false, length=255)
	private String acadStudStatus;

	@Column(name="ACAD_STUD_TITULE", nullable=false, length=255)
	private String acadStudTitule;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CITY_CITY_ID", nullable=false)
	private City city;

	//bi-directional many-to-one association to Curriculum
	@ManyToOne
	@JoinColumn(name="CURRICULUM_CURR_ID")
	private Curriculum curriculum;

	public Academicstudy() {
	}

	public long getAcadStudId() {
		return this.acadStudId;
	}

	public void setAcadStudId(long acadStudId) {
		this.acadStudId = acadStudId;
	}

	public Date getAcadStudEndDate() {
		return this.acadStudEndDate;
	}

	public void setAcadStudEndDate(Date acadStudEndDate) {
		this.acadStudEndDate = acadStudEndDate;
	}

	public String getAcadStudInsti() {
		return this.acadStudInsti;
	}

	public void setAcadStudInsti(String acadStudInsti) {
		this.acadStudInsti = acadStudInsti;
	}

	public String getAcadStudLevel() {
		return this.acadStudLevel;
	}

	public void setAcadStudLevel(String acadStudLevel) {
		this.acadStudLevel = acadStudLevel;
	}

	public Date getAcadStudStartDate() {
		return this.acadStudStartDate;
	}

	public void setAcadStudStartDate(Date acadStudStartDate) {
		this.acadStudStartDate = acadStudStartDate;
	}

	public String getAcadStudStatus() {
		return this.acadStudStatus;
	}

	public void setAcadStudStatus(String acadStudStatus) {
		this.acadStudStatus = acadStudStatus;
	}

	public String getAcadStudTitule() {
		return this.acadStudTitule;
	}

	public void setAcadStudTitule(String acadStudTitule) {
		this.acadStudTitule = acadStudTitule;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
