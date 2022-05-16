package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the CONTACT database table.
 */
@Entity
@Table(name = "CONTACT")
@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CONTACT_CONTID_GENERATOR", allocationSize = 1, sequenceName = "CONTACT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_CONTID_GENERATOR")
    @Column(name = "CONT_ID", unique = true, nullable = false, precision = 10)
    private long contId;

    @Column(name = "CONT_EMAIL", length = 255, unique = true)
    private String contEmail;

    @Column(name = "CONT_NAME", length = 255)
    private String contName;

    @Column(name = "CONT_PHONE", length = 20)
    private String contPhone;

    @Column(name = "CONT_POSITION", length = 255)
    private String contPosition;

    //bi-directional many-to-one association to Company
    @ManyToOne
    @JoinColumn(name = "COMPANY_COMP_ID")
    //@JsonIgnore
    private Company company;

    public Contact() {
    }

    public long getContId() {
        return this.contId;
    }

    public void setContId(long contId) {
        this.contId = contId;
    }

    public String getContEmail() {
        return this.contEmail;
    }

    public void setContEmail(String contEmail) {
        this.contEmail = contEmail;
    }

    public String getContName() {
        return this.contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getContPhone() {
        return this.contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }

    public String getContPosition() {
        return this.contPosition;
    }

    public void setContPosition(String contPosition) {
        this.contPosition = contPosition;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
