package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the NOTI database table.
 */
@Entity
@Table(name = "NOTI")
@NamedQuery(name = "Noti.findAll", query = "SELECT n FROM Noti n")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Noti implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "NOTI_NOTIID_GENERATOR", allocationSize = 1, sequenceName = "NOTI_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTI_NOTIID_GENERATOR")
    @Column(name = "NOTI_ID", unique = true, nullable = false, precision = 10)
    private long notiId;

    @Temporal(TemporalType.DATE)
    @Column(name = "NOTI_DATE", nullable = true)
    private Date notiDate;

    @Column(name = "NOTI_DESCRIPTION", length = 1000, nullable = true)
    private String notiDescription;

    @Column(name = "NOTI_EMAIL_DESTINATION", length = 255, nullable = true)
    private String notiEmailDestination;

    @Column(name = "NOTI_EMAIL_SOURCE", length = 255, nullable = true)
    private String notiEmailSource;

    @Column(name = "NOTI_LOGICALOPERAND", length = 1, nullable = true)
    private String notiLogicaloperand;

    @Column(name = "NOTI_SUBJECT", length = 255, nullable = true)
    private String notiSubject;

    @Column(name = "NOTI_START_DATE", nullable = true)
    private Date notiStartDate;

    @Column(name = "NOTI_END_DATE", nullable = true)
    private Date notiEndDate;

    //bi-directional many-to-many association to Company
    @ManyToMany
    @JoinTable(
            name = "COMP_NOTI"
            , joinColumns = {
            @JoinColumn(name = "NOTI_NOTI_ID", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "COMPANY_COMP_ID", nullable = false)
    }
    )
    @JsonIgnore
    private List<Company> companies;

    //bi-directional many-to-one association to NotiType
    @OneToMany(mappedBy = "noti")
    @JsonIgnore
    private List<NotiType> notiTypes;

    //bi-directional many-to-one association to Precondition
    @OneToMany(mappedBy = "noti")
    @JsonIgnore
    private List<Precondition> preconditions;

    //bi-directional many-to-many association to Triggerr
    @ManyToMany(mappedBy = "notis")
    @JsonIgnore
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


    public Date getNotiStartDate() {
        return notiStartDate;
    }

    public void setNotiStartDate(Date notiStartDate) {
        this.notiStartDate = notiStartDate;
    }

    public Date getNotiEndDate() {
        return notiEndDate;
    }

    public void setNotiEndDate(Date notiEndDate) {
        this.notiEndDate = notiEndDate;
    }
}
