package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the NOTI database table.
 * 
 */
@Entity
@Table(name="NOTI")
@NamedQuery(name="Noti.findAll", query="SELECT n FROM Noti n")
public class Noti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOTI_NOTIID_GENERATOR", sequenceName = "NOTI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTI_NOTIID_GENERATOR")
	@Column(name="NOTI_ID", unique=true, nullable=false, precision=10)
	private long notiId;

	@Temporal(TemporalType.DATE)
	@Column(name="NOTI_DATE", nullable=false)
	private Date notiDate;

	@Column(name="NOTI_DESCRIPTION", nullable=false, length=1000)
	private String notiDescription;

	@Column(name="NOTI_EMAIL_DESTINATION", nullable=false, length=255)
	private String notiEmailDestination;

	@Column(name="NOTI_EMAIL_SOURCE", nullable=false, length=255)
	private String notiEmailSource;

	@Column(name="NOTI_LOGICALOPERAND", length=255)
	private String notiLogicaloperand;

	@Column(name="NOTI_SUBJECT", nullable=false, length=255)
	private String notiSubject;

	//bi-directional many-to-many association to Company
	@ManyToMany
	@JoinTable(
		name="COMP_NOTI"
		, joinColumns={
			@JoinColumn(name="NOTI_NOTI_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="COMPANY_COMP_ID", nullable=false)
			}
		)
	private List<Company> companies;

	//bi-directional many-to-one association to NotiType
	@OneToMany(mappedBy="noti")
	private List<NotiType> notiTypes;

	//bi-directional many-to-one association to Precondition
	@OneToMany(mappedBy="noti")
	private List<Precondition> preconditions;

	//bi-directional many-to-many association to Triggerr
	@ManyToMany(mappedBy="notis")
	private List<Triggerr> triggerrs;

	public Noti() {
	}

	public long getNotiId() {
		return this.notiId;
	}

	public void setNotiId(long notiId) {
		this.notiId = notiId;
	}

	public Date getNotiDate() {
		return this.notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public String getNotiDescription() {
		return this.notiDescription;
	}

	public void setNotiDescription(String notiDescription) {
		this.notiDescription = notiDescription;
	}

	public String getNotiEmailDestination() {
		return this.notiEmailDestination;
	}

	public void setNotiEmailDestination(String notiEmailDestination) {
		this.notiEmailDestination = notiEmailDestination;
	}

	public String getNotiEmailSource() {
		return this.notiEmailSource;
	}

	public void setNotiEmailSource(String notiEmailSource) {
		this.notiEmailSource = notiEmailSource;
	}

	public String getNotiLogicaloperand() {
		return this.notiLogicaloperand;
	}

	public void setNotiLogicaloperand(String notiLogicaloperand) {
		this.notiLogicaloperand = notiLogicaloperand;
	}

	public String getNotiSubject() {
		return this.notiSubject;
	}

	public void setNotiSubject(String notiSubject) {
		this.notiSubject = notiSubject;
	}

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<NotiType> getNotiTypes() {
		return this.notiTypes;
	}

	public void setNotiTypes(List<NotiType> notiTypes) {
		this.notiTypes = notiTypes;
	}

	public NotiType addNotiType(NotiType notiType) {
		getNotiTypes().add(notiType);
		notiType.setNoti(this);

		return notiType;
	}

	public NotiType removeNotiType(NotiType notiType) {
		getNotiTypes().remove(notiType);
		notiType.setNoti(null);

		return notiType;
	}

	public List<Precondition> getPreconditions() {
		return this.preconditions;
	}

	public void setPreconditions(List<Precondition> preconditions) {
		this.preconditions = preconditions;
	}

	public Precondition addPrecondition(Precondition precondition) {
		getPreconditions().add(precondition);
		precondition.setNoti(this);

		return precondition;
	}

	public Precondition removePrecondition(Precondition precondition) {
		getPreconditions().remove(precondition);
		precondition.setNoti(null);

		return precondition;
	}

	public List<Triggerr> getTriggerrs() {
		return this.triggerrs;
	}

	public void setTriggerrs(List<Triggerr> triggerrs) {
		this.triggerrs = triggerrs;
	}

}
