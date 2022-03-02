package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SKILL database table.
 * 
 */
@Entity
@Table(name="SKILL")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SKILL_SKILID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SKILL_SKILID_GENERATOR")
	@Column(name="SKIL_ID", unique=true, nullable=false, precision=10)
	private long skilId;

	@Column(name="SKIL_DESCRIPTION", length=1000)
	private String skilDescription;

	@Column(name="SKIL_NAME", nullable=false, length=255)
	private String skilName;

	//bi-directional many-to-one association to Curriculum
	@ManyToOne
	@JoinColumn(name="CURRICULUM_CURR_ID")
	private Curriculum curriculum;

	//bi-directional many-to-one association to InternRequest
	@ManyToOne
	@JoinColumn(name="INTERN_REQUEST_INTE_REQU_ID")
	private InternRequest internRequest;

	public Skill() {
	}

	public long getSkilId() {
		return this.skilId;
	}

	public void setSkilId(long skilId) {
		this.skilId = skilId;
	}

	public String getSkilDescription() {
		return this.skilDescription;
	}

	public void setSkilDescription(String skilDescription) {
		this.skilDescription = skilDescription;
	}

	public String getSkilName() {
		return this.skilName;
	}

	public void setSkilName(String skilName) {
		this.skilName = skilName;
	}

	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public InternRequest getInternRequest() {
		return this.internRequest;
	}

	public void setInternRequest(InternRequest internRequest) {
		this.internRequest = internRequest;
	}

}
