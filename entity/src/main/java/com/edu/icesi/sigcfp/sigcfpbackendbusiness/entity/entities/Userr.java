package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the USERR database table.
 */
@Entity
@Table(name = "USERR")
@NamedQuery(name = "Userr.findAll", query = "SELECT u FROM Userr u")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Userr implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "USERR_USERID_GENERATOR", allocationSize = 1, sequenceName = "USERR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERR_USERID_GENERATOR")
    @Column(name = "USER_ID", unique = true, nullable = false, precision = 10)
    private long userId;

    @Column(name = "USER_NAME", length = 255, unique = true)
    private String userName;

    @Column(name = "USER_PASSWORD", length = 1000, nullable = true)
    private String userPassword;

    @Column(name = "ISENABLE", length = 2, nullable = true)
    private boolean isEnable;

    @OneToOne(optional = true)
    @JoinColumn(name = "PERSON_PERS_ID", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    //@JsonIgnore
    private Person person;

    //bi-directional many-to- one association to Rolee
    //@OneToMany(mappedBy="userr")
    //@JsonIgnore
    //private List<Rolee> rolees;
    @ManyToOne
    @JoinColumn(name = "ROLE_ROLEE_ID", nullable = true)
    private Rolee rolee;

    //bi-directional many-to-one association to Company
    //@ManyToOne(fetch = FetchType.LAZY)
    @OneToOne()
    @JoinColumn(name = "COMPANY_COMP_ID", nullable = true)
    //@JsonIgnore
    private Company company;

    public Userr() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Rolee getRolee() {
        return this.rolee;
    }

    public void setRolee(Rolee rolee) {
        this.rolee = rolee;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
