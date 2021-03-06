package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the COMPANY database table.
 */
@Entity
@Table(name = "COMPANY")
@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "COMPANY_COMPID_GENERATOR", allocationSize = 1, sequenceName = "COMPANY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_COMPID_GENERATOR")
    @Column(name = "COMP_ID", unique = true, nullable = false, precision = 10)
    private long compId;

    @Column(name = "COMP_ADDRESS", length = 255)
    private String compAddress;

    @Column(name = "COMP_ECO_ACTIV", length = 255)
    private String compEcoActiv;

    @Column(name = "COMP_EMAIL", length = 100)
    private String compEmail;

    @Column(name = "COMP_ICESI_STUD", length = 1)
    private String compIcesiStud;

    @Column(name = "COMP_NAME", length = 255)
    private String compName;

    @Column(name = "COMP_NIT", length = 255)
    private String compNit;

    @Column(name = "COMP_TELEPHONE", length = 20)
    private String compTelephone;

    @Column(name = "COMP_TYPE", length = 255)
    private String compType;

    @Column(name = "COMP_URL_ADDRESS", length = 255)
    private String compUrlAddress;

    @Column(name = "COMP_COUNTRY_NAME")
    private String compCountryName;

    @Column(name = "COMP_CITY_NAME")
    private String compCityName;

    //bi-directional many-to-one association to Userr
    //@ManyToOne
    //@JoinColumn(name="USERR_USER_ID")
    //@JsonIgnore
    //@OneToOne(mappedBy = "company")
    private Userr userr;

    //bi-directional many-to-one association to Contact
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Contact> contacts;

    //bi-directional many-to-one association to Curriculum
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Curriculum> curriculums;

    //bi-directional many-to-one association to InternRequest
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<InternRequest> internRequests;

    //bi-directional many-to-many association to Noti
    @ManyToMany(mappedBy = "companies")
    @JsonIgnore
    private List<Noti> notis;

    //bi-directional many-to-one association to Userr
    //@OneToMany(mappedBy="company")
    //@JsonIgnore
    //private List<Userr> userrs;

    public Company() {
    }

    public long getCompId() {
        return this.compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getCompAddress() {
        return this.compAddress;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress;
    }

    public String getCompEcoActiv() {
        return this.compEcoActiv;
    }

    public void setCompEcoActiv(String compEcoActiv) {
        this.compEcoActiv = compEcoActiv;
    }

    public String getCompEmail() {
        return this.compEmail;
    }

    public void setCompEmail(String compEmail) {
        this.compEmail = compEmail;
    }

    public String getCompIcesiStud() {
        return this.compIcesiStud;
    }

    public void setCompIcesiStud(String compIcesiStud) {
        this.compIcesiStud = compIcesiStud;
    }

    public String getCompName() {
        return this.compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompNit() {
        return this.compNit;
    }

    public void setCompNit(String compNit) {
        this.compNit = compNit;
    }

    public String getCompTelephone() {
        return this.compTelephone;
    }

    public void setCompTelephone(String compTelephone) {
        this.compTelephone = compTelephone;
    }

    public String getCompType() {
        return this.compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getCompUrlAddress() {
        return this.compUrlAddress;
    }

    public void setCompUrlAddress(String compUrlAddress) {
        this.compUrlAddress = compUrlAddress;
    }

    public String getCompCountryName() {
        return compCountryName;
    }

    public void setCompCountryName(String compCountryName) {
        this.compCountryName = compCountryName;
    }

    public String getCompCityName() {
        return compCityName;
    }

    public void setCompCityName(String compCityName) {
        this.compCityName = compCityName;
    }

    public Userr getUserr() {
        return this.userr;
    }

    public void setUserr(Userr userr) {
        this.userr = userr;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contact addContact(Contact contact) {
        getContacts().add(contact);
        contact.setCompany(this);

        return contact;
    }

    public Contact removeContact(Contact contact) {
        getContacts().remove(contact);
        contact.setCompany(null);

        return contact;
    }

    public List<Curriculum> getCurriculums() {
        return this.curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public Curriculum addCurriculum(Curriculum curriculum) {
        getCurriculums().add(curriculum);
        curriculum.setCompany(this);

        return curriculum;
    }

    public Curriculum removeCurriculum(Curriculum curriculum) {
        getCurriculums().remove(curriculum);
        curriculum.setCompany(null);

        return curriculum;
    }

    public List<InternRequest> getInternRequests() {
        return this.internRequests;
    }

    public void setInternRequests(List<InternRequest> internRequests) {
        this.internRequests = internRequests;
    }

    public InternRequest addInternRequest(InternRequest internRequest) {
        getInternRequests().add(internRequest);
        internRequest.setCompany(this);

        return internRequest;
    }

    public InternRequest removeInternRequest(InternRequest internRequest) {
        getInternRequests().remove(internRequest);
        internRequest.setCompany(null);

        return internRequest;
    }

    public List<Noti> getNotis() {
        return this.notis;
    }

    public void setNotis(List<Noti> notis) {
        this.notis = notis;
    }
/**
 *
 public List<Userr> getUserrs() {
 return this.userrs;
 }

 public void setUserrs(List<Userr> userrs) {
 this.userrs = userrs;
 }

 public Userr addUserr(Userr userr) {
 getUserrs().add(userr);
 userr.setCompany(this);

 return userr;
 }

 public Userr removeUserr(Userr userr) {
 getUserrs().remove(userr);
 userr.setCompany(null);

 return userr;
 }
 * @return
 */

}
