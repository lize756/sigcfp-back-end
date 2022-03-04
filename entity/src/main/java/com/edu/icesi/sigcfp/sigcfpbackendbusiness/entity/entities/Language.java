package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "LANGUAGE" database table.
 * 
 */
@Entity
@Table(name="\"LANGUAGE\"")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LANGUAGE_LANGUID_GENERATOR", sequenceName = "LANGUAGE_SEQ", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LANGUAGE_LANGUID_GENERATOR")
	@Column(name="LANGU_ID", unique=true, nullable=false, precision=10)
	private long languId;

	@Column(name="LANGU_LEVEL", nullable=false, length=255)
	private String languLevel;

	@Column(name="LANGU_NAME", nullable=false, length=255)
	private String languName;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSON_PERS_ID")
	private Person person;

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

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
