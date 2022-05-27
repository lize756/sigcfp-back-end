package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class InternRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "INTERN_REQUEST_INTEREQUID_GENERATOR", allocationSize = 1, sequenceName = "INTERN_REQUEST_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTERN_REQUEST_INTEREQUID_GENERATOR")
    @Column(name = "INTE_REQU_ID", unique = true, nullable = false, precision = 10)
    private long inteRequId;

    @Column(name = "INTE_REQU_BONDING_TYPE", length = 50, nullable = true)
    private String inteRequBondingType;

    private String inteRequDetails;
    @Column(name = "INTE_REQU_COMPETENCIES", length = 1000, nullable = true)
    private String inteRequCompetencies;

    // @Temporal(TemporalType.DATE)
    // @JsonFormat(pattern="dd/MM/yy")
    @Column(name = "INTE_REQU_CREATE", nullable = true)
    private Date inteRequCreate;

    @Column(name = "INTE_REQU_DEPARTMENT", length = 50, nullable = true)
    private String inteRequDepartment;

    @Column(name = "INTE_REQU_DURATION", length = 15, nullable = true)
    private String inteRequDuration;

    @Column(name = "INTE_REQU_FUNCTIONS", length = 1000, nullable = true)
    private String inteRequFunctions;

    @Column(name = "INTE_REQU_ISINPROCESS", length = 1, nullable = true)
    private String inteRequIsinprocess;

    @Column(name = "INTE_REQU_NAME", nullable = true, length = 255)
    private String inteRequName;

    @Column(name = "INTE_REQU_NUMBER", precision = 5, nullable = true)
    private BigDecimal inteRequNumber;

    @Column(name = "INTE_REQU_OTHER_BENEFITS", length = 1000, nullable = true)
    private String inteRequOtherBenefits;

    @Column(name = "INTE_REQU_STATUS", length = 20, nullable = true)
    private String inteRequStatus;

    @Column(name = "INTE_REQU_LOCATION", length = 20, nullable = true)
    private String inteRequLocation;

    @Column(name = "INTE_REQU_SALARY", precision = 9, nullable = true)
    private BigDecimal inteRequSalary;

    // @Temporal(TemporalType.DATE)
    // @JsonFormat(pattern="dd/MM/yy")
    @Column(name = "INTE_REQU_ST_DATE", nullable = true)
    private Date inteRequStDate;

    // bi-directional many-to-many association to Career
    @ManyToMany
    @JoinTable(name = "INT_REQ_CARE", joinColumns = {
            @JoinColumn(name = "INTERN_REQUEST_INTE_REQU_ID", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "CAREER_CARE_ID", nullable = false)})
    // @JsonIgnore
    private List<Career> careers;

    // bi-directional many-to-one association to Company
    @ManyToOne
    @JoinColumn(name = "COMPANY_COMP_ID", nullable = true)
    // @JsonIgnore
    private Company company;

    @Column(name = "INTE_REQU_COUNTRY_NAME", length = 20, nullable = true)
    private String inteRequCountryName;

    @Column(name = "INTE_REQU_CITY_NAME", length = 20, nullable = true)
    private String inteRequCityName;

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

    public List<Career> getCareers() {
        return this.careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public Career addCareers1(Career careers) {
        getCareers().add(careers);
        careers.setInternRequest(this);

        return careers;
    }

    public Career removeCareers1(Career careers) {
        getCareers().remove(careers);
        careers.setInternRequest(null);
        return careers;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getInteRequStatus() {
        return inteRequStatus;
    }

    public void setInteRequStatus(String inteRequStatus) {
        this.inteRequStatus = inteRequStatus;
    }

    public String getInteRequLocation() {
        return inteRequLocation;
    }

    public void setInteRequLocation(String inteRequLocation) {
        this.inteRequLocation = inteRequLocation;
    }

    public String getInteRequCountryName() {
        return inteRequCountryName;
    }

    public void setInteRequCountryName(String inteRequCountryName) {
        this.inteRequCountryName = inteRequCountryName;
    }

    public String getInteRequCityName() {
        return inteRequCityName;
    }

    public void setInteRequCityName(String inteRequCityName) {
        this.inteRequCityName = inteRequCityName;
    }

}
