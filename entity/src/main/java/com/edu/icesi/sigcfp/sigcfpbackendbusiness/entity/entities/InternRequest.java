package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the INTERN_REQUEST database table.
 * 
 */
@Entity
@Table(name="INTERN_REQUEST")
@NamedQuery(name="InternRequest.findAll", query="SELECT i FROM InternRequest i")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class InternRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INTERN_REQUEST_INTEREQUID_GENERATOR", allocationSize = 1, sequenceName = "INTERN_REQUEST_SEQ" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INTERN_REQUEST_INTEREQUID_GENERATOR")
	@Column(name="INTE_REQU_ID", unique=true, nullable=false, precision=10)
	private long inteRequId;

	@Column(name="INTE_REQU_BONDING_TYPE", length=50)
	private String inteRequBondingType;

	@Column(name="INTE_REQU_COMPETENCIES", length=1000)
	private String inteRequCompetencies;

	@Temporal(TemporalType.DATE)
	@Column(name="INTE_REQU_CREATE")
	private Date inteRequCreate;

	@Column(name="INTE_REQU_DEPARTMENT", length=50)
	private String inteRequDepartment;

	@Column(name="INTE_REQU_DETAILS", nullable=false, length=1000)
	private String inteRequDetails;

	@Column(name="INTE_REQU_DURATION", length=15)
	private String inteRequDuration;

	@Column(name="INTE_REQU_FUNCTIONS", length=1000)
	private String inteRequFunctions;

	@Column(name="INTE_REQU_ISINPROCESS", length=1)
	private String inteRequIsinprocess;

	@Column(name="INTE_REQU_NAME", nullable=false, length=255)
	private String inteRequName;

	@Column(name="INTE_REQU_NAME1", length=100)
	private String inteRequName1;

	@Column(name="INTE_REQU_NUMBER", precision=5)
	private BigDecimal inteRequNumber;

	@Column(name="INTE_REQU_OTHER_BENEFITS", length=1000)
	private String inteRequOtherBenefits;

	@Column(name="INTE_REQU_SALARY", precision=9)
	private BigDecimal inteRequSalary;

	@Temporal(TemporalType.DATE)
	@Column(name="INTE_REQU_ST_DATE")
	private Date inteRequStDate;

	//bi-directional many-to-one association to Career
	@OneToMany(mappedBy="internRequest")
	@JsonIgnore
	private List<Career> careers1;

	//bi-directional many-to-many association to Career
	@ManyToMany
	@JoinTable(
		name="INT_REQ_CARE"
		, joinColumns={
			@JoinColumn(name="INTERN_REQUEST_INTE_REQU_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="CAREER_CARE_ID", nullable=false)
			}
		)
	@JsonIgnore
	private List<Career> careers2;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY_COMP_ID")
	@JsonIgnore
	private Company company;

	public InternRequest() {
	}

	public long getInteRequId() {
		return this.inteRequId;
	}

	public void setInteRequId(long inteRequId) {
		this.inteRequId = inteRequId;
	}

	public String getInteRequBondingType() {
		return this.inteRequBondingType;
	}

	public void setInteRequBondingType(String inteRequBondingType) {
		this.inteRequBondingType = inteRequBondingType;
	}

	public String getInteRequCompetencies() {
		return this.inteRequCompetencies;
	}

	public void setInteRequCompetencies(String inteRequCompetencies) {
		this.inteRequCompetencies = inteRequCompetencies;
	}

	public Date getInteRequCreate() {
		return this.inteRequCreate;
	}

	public void setInteRequCreate(Date inteRequCreate) {
		this.inteRequCreate = inteRequCreate;
	}

	public String getInteRequDepartment() {
		return this.inteRequDepartment;
	}

	public void setInteRequDepartment(String inteRequDepartment) {
		this.inteRequDepartment = inteRequDepartment;
	}

	public String getInteRequDetails() {
		return this.inteRequDetails;
	}

	public void setInteRequDetails(String inteRequDetails) {
		this.inteRequDetails = inteRequDetails;
	}

	public String getInteRequDuration() {
		return this.inteRequDuration;
	}

	public void setInteRequDuration(String inteRequDuration) {
		this.inteRequDuration = inteRequDuration;
	}

	public String getInteRequFunctions() {
		return this.inteRequFunctions;
	}

	public void setInteRequFunctions(String inteRequFunctions) {
		this.inteRequFunctions = inteRequFunctions;
	}

	public String getInteRequIsinprocess() {
		return this.inteRequIsinprocess;
	}

	public void setInteRequIsinprocess(String inteRequIsinprocess) {
		this.inteRequIsinprocess = inteRequIsinprocess;
	}

	public String getInteRequName() {
		return this.inteRequName;
	}

	public void setInteRequName(String inteRequName) {
		this.inteRequName = inteRequName;
	}

	public String getInteRequName1() {
		return this.inteRequName1;
	}

	public void setInteRequName1(String inteRequName1) {
		this.inteRequName1 = inteRequName1;
	}

	public BigDecimal getInteRequNumber() {
		return this.inteRequNumber;
	}

	public void setInteRequNumber(BigDecimal inteRequNumber) {
		this.inteRequNumber = inteRequNumber;
	}

	public String getInteRequOtherBenefits() {
		return this.inteRequOtherBenefits;
	}

	public void setInteRequOtherBenefits(String inteRequOtherBenefits) {
		this.inteRequOtherBenefits = inteRequOtherBenefits;
	}

	public BigDecimal getInteRequSalary() {
		return this.inteRequSalary;
	}

	public void setInteRequSalary(BigDecimal inteRequSalary) {
		this.inteRequSalary = inteRequSalary;
	}

	public Date getInteRequStDate() {
		return this.inteRequStDate;
	}

	public void setInteRequStDate(Date inteRequStDate) {
		this.inteRequStDate = inteRequStDate;
	}

	public List<Career> getCareers1() {
		return this.careers1;
	}

	public void setCareers1(List<Career> careers1) {
		this.careers1 = careers1;
	}

	public Career addCareers1(Career careers1) {
		getCareers1().add(careers1);
		careers1.setInternRequest(this);

		return careers1;
	}

	public Career removeCareers1(Career careers1) {
		getCareers1().remove(careers1);
		careers1.setInternRequest(null);

		return careers1;
	}

	public List<Career> getCareers2() {
		return this.careers2;
	}

	public void setCareers2(List<Career> careers2) {
		this.careers2 = careers2;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
