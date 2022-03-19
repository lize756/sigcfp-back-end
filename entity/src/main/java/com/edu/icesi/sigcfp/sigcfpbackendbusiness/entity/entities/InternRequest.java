package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the INTERN_REQUEST database table.
 */
@Entity
@Table(name = "INTERN_REQUEST")
@NamedQuery(name = "InternRequest.findAll", query = "SELECT i FROM InternRequest i")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class InternRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "INTERN_REQUEST_INTEREQUID_GENERATOR", sequenceName = "INTERN_REQUEST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTERN_REQUEST_INTEREQUID_GENERATOR")
    @Column(name = "INTE_REQU_ID", unique = true, nullable = false, precision = 10)
    private long inteRequId;

    @Temporal(TemporalType.DATE)
    @Column(name = "INTE_REQU_CREATE", nullable = false)
    private Date inteRequCreate;

    @Column(name = "INTE_REQU_DETAILS", nullable = false, length = 1000)
    private String inteRequDetails;

    @Column(name = "INTE_REQU_DURATION", nullable = false, length = 15)
    private String inteRequDuration;

    @Column(name = "INTE_REQU_ISINPROCESS", nullable = false, length = 1)
    private String inteRequIsinprocess;

    @Column(name = "INTE_REQU_NAME", nullable = false, length = 255)
    private String inteRequName;
    
    @Column(name = "INTE_REQU_NUMBER", nullable = false, precision = 5)
    private BigDecimal inteRequNumber;

    @Column(name = "INTE_REQU_SALARY", nullable = false, precision = 9)
    private BigDecimal inteRequSalary;
    
    
    //---------------New ---------------------------
	@Column(name = "INTE_REQU_DEPARTMENT", nullable = true, length = 50)
    private String inteRequDepartament;

	@Column(name = "INTE_REQU_ST_DATE", nullable = true)
    private Date inteRequStDate;
    
    @Column(name = "INTE_REQU_FUNCTIONS", nullable = true, length = 1000)
    private String inteRequFunctions;
    
    
    @Column(name = "INTE_REQU_COMPETENCIES", nullable = true, length = 1000)
    private String inteRequCompetencies;
    
    
    @Column(name = "INTE_REQU_BONDING_TYPE", nullable = true, length = 50)
    private String inteRequBondingType;


    @Column(name = "INTE_REQU_OTHER_BENEFITS", nullable = true, length = 50)
    private String inteRequOtherBenefits; 
    
    
    
    //bi-directional many-to-one association to Career
    @OneToMany(mappedBy = "internRequest")
    @JsonIgnore
    private List<Career> careers;

    //bi-directional many-to-one association to Company
    @ManyToOne
    @JoinColumn(name = "COMPANY_COMP_ID")
    @JsonIgnore
    private Company company;

    //bi-directional many-to-one association to Skill
    @OneToMany(mappedBy = "internRequest")
    @JsonIgnore
    private List<Skill> skills;

    public InternRequest() {
    }

    public long getInteRequId() {
        return this.inteRequId;
    }

    public void setInteRequId(long inteRequId) {
        this.inteRequId = inteRequId;
    }

    public Date getInteRequCreate() {
        return this.inteRequCreate;
    }

    public void setInteRequCreate(Date inteRequCreate) {
        this.inteRequCreate = inteRequCreate;
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

    public BigDecimal getInteRequNumber() {
        return this.inteRequNumber;
    }

    public void setInteRequNumber(BigDecimal inteRequNumber) {
        this.inteRequNumber = inteRequNumber;
    }

    public BigDecimal getInteRequSalary() {
        return this.inteRequSalary;
    }

    public void setInteRequSalary(BigDecimal inteRequSalary) {
        this.inteRequSalary = inteRequSalary;
    }
    
    
    public String getInteRequDepartament() {
		return inteRequDepartament;
	}

	public void setInteRequDepartament(String inteRequDepartament) {
		this.inteRequDepartament = inteRequDepartament;
	}

	public Date getInteRequStDate() {
		return inteRequStDate;
	}

	public void setInteRequStDate(Date inteRequStDate) {
		this.inteRequStDate = inteRequStDate;
	}

	public String getInteRequFunctions() {
		return inteRequFunctions;
	}

	public void setInteRequFunctions(String inteRequFunctions) {
		this.inteRequFunctions = inteRequFunctions;
	}

	public String getInteRequCompetencies() {
		return inteRequCompetencies;
	}

	public void setInteRequCompetencies(String inteRequCompetencies) {
		this.inteRequCompetencies = inteRequCompetencies;
	}

	public String getInteRequBondingType() {
		return inteRequBondingType;
	}

	public void setInteRequBondingType(String inteRequBondingType) {
		this.inteRequBondingType = inteRequBondingType;
	}

	public String getInteRequOtherBenefits() {
		return inteRequOtherBenefits;
	}

	public void setInteRequOtherBenefits(String inteRequOtherBenefits) {
		this.inteRequOtherBenefits = inteRequOtherBenefits;
	}
    

    public List<Career> getCareers() {
        return this.careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public Career addCareer(Career career) {
        getCareers().add(career);
        career.setInternRequest(this);

        return career;
    }

    public Career removeCareer(Career career) {
        getCareers().remove(career);
        career.setInternRequest(null);

        return career;
    }
    
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Skill addSkill(Skill skill) {
        getSkills().add(skill);
        skill.setInternRequest(this);

        return skill;
    }

    public Skill removeSkill(Skill skill) {
        getSkills().remove(skill);
        skill.setInternRequest(null);

        return skill;
    }

}
