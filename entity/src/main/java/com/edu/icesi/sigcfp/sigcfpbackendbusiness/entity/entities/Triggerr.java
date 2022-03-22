package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TRIGGERR database table.
 * 
 */
@Entity
@Table(name="TRIGGERR")
@NamedQuery(name="Triggerr.findAll", query="SELECT t FROM Triggerr t")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Triggerr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRIGGERR_TRIGID_GENERATOR", allocationSize = 1, sequenceName = "TRIGGERR_SEQ" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRIGGERR_TRIGID_GENERATOR")
	@Column(name="TRIG_ID", unique=true, nullable=false, precision=10)
	private long trigId;

	@Column(name="TRIG_DESCRIPTION", length=1000)
	private String trigDescription;

	@Column(name="TRIG_NAME", length=255)
	private String trigName;

	@Column(name="TRIG_SCOPE", length=255)
	private String trigScope;

	//bi-directional many-to-many association to Noti
	@ManyToMany
	@JoinTable(
		name="NOTI_AUTO"
		, joinColumns={
			@JoinColumn(name="TRIGGERR_TRIG_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="NOTI_NOTI_ID", nullable=false)
			}
		)
	@JsonIgnore
	private List<Noti> notis;

	public Triggerr() {
	}

	public long getTrigId() {
		return this.trigId;
	}

	public void setTrigId(long trigId) {
		this.trigId = trigId;
	}

	public String getTrigDescription() {
		return this.trigDescription;
	}

	public void setTrigDescription(String trigDescription) {
		this.trigDescription = trigDescription;
	}

	public String getTrigName() {
		return this.trigName;
	}

	public void setTrigName(String trigName) {
		this.trigName = trigName;
	}

	public String getTrigScope() {
		return this.trigScope;
	}

	public void setTrigScope(String trigScope) {
		this.trigScope = trigScope;
	}

	public List<Noti> getNotis() {
		return this.notis;
	}

	public void setNotis(List<Noti> notis) {
		this.notis = notis;
	}

}
