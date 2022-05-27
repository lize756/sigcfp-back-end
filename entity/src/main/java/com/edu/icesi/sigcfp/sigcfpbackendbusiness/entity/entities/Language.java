package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the "language" database table.
 */
@Entity
@Table(name = "LANGUAGE")
@NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "LANGUAGE_LANGUID_GENERATOR", allocationSize = 1, sequenceName = "language_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_LANGUID_GENERATOR")
    @Column(name = "LANGU_ID", unique = true, nullable = false, precision = 19)
    private long languId;

    @Column(name = "LANGU_LEVEL", nullable = true, length = 255)
    private String languLevel;

    @Column(name = "LANGU_NAME", nullable = true, length = 255)
    private String languName;
    
    @Column(name = "LANGU_INSTITUTION_NAME", nullable = true, length = 255)
    private String languInstitutionName;
    
    @Column(name = "LANGU_END_DATE", nullable = true)
    private Date languEndDate;

 // bi-directional many-to-one association to Curriculum
 	@ManyToOne
 	@JoinColumn(name = "CURRICULUM_CURR_ID", nullable = true)
 	@JsonIgnore
	private Curriculum curriculum;


    public Language() {
    }

    public long getLanguId() {
        return this.languId;
    }

    public void setLanguId(long languId) {
        this.languId = languId;
    }

    public String getLanguLevel() {
        return this.languLevel;
    }

    public void setLanguLevel(String languLevel) {
        this.languLevel = languLevel;
    }

    public String getLanguName() {
        return this.languName;
    }

    public void setLanguName(String languName) {
        this.languName = languName;
    }

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public String getLanguInstitutionName() {
		return languInstitutionName;
	}

	public Date getLanguEndDate() {
		return languEndDate;
	}

	public void setLanguInstitutionName(String languInstitutionName) {
		this.languInstitutionName = languInstitutionName;
	}

	public void setLanguEndDate(Date languEndDate) {
		this.languEndDate = languEndDate;
	}



}
