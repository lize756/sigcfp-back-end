package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the ACADEMICSTUDY database table.
 */
@Entity
@Table(name = "ACADEMICSTUDY")
@NamedQuery(name = "Academicstudy.findAll", query = "SELECT a FROM Academicstudy a")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Academicstudy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACADEMICSTUDY_ACADSTUDID_GENERATOR", allocationSize = 1, sequenceName = "ACADEMICSTUDY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACADEMICSTUDY_ACADSTUDID_GENERATOR")
    @Column(name = "ACAD_STUD_ID", unique = true, nullable = false, precision = 10)
    private long acadStudId;

    @Temporal(TemporalType.DATE)
    @Column(name = "ACAD_STUD_END_DATE")
    private Date acadStudEndDate;

    @Column(name = "ACAD_STUD_INSTI", length = 255)
    private String acadStudInsti;

    @Column(name = "ACAD_STUD_LEVEL", length = 255)
    private String acadStudLevel;

    @Temporal(TemporalType.DATE)
    @Column(name = "ACAD_STUD_START_DATE")
    private Date acadStudStartDate;

    @Column(name = "ACAD_STUD_STATUS", length = 10)
    private String acadStudStatus;

    @Column(name = "ACAD_STUD_TITULE", length = 255)
    private String acadStudTitule;

	


	//bi-directional many-to-one association to Curriculum
	@ManyToOne
	@JoinColumn(name="CURRICULUM_CURR_ID")
	@JsonIgnore
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

	public String getAcadCountryName() {
		return acadCountryName;
	}

	public void setAcadCountryName(String acadCountryName) {
		this.acadCountryName = acadCountryName;
	}

	public String getAcadCityName() {
		return acadCityName;
	}

	public void setAcadCityName(String acadCityName) {
		this.acadCityName = acadCityName;
	}

	@Column(name="ACAD_COUNTRY_NAME")
	private String acadCountryName;
	
	@Column(name="ACAD_CITY_NAME")
	private String acadCityName;



	public Curriculum getCurriculum() {
		return this.curriculum;
	}

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

}
