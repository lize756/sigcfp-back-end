package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the ETHNICGROUP database table.
 */
@Entity
@Table(name = "ETHNICGROUP")
@NamedQuery(name = "Ethnicgroup.findAll", query = "SELECT e FROM Ethnicgroup e")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Ethnicgroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ETHNICGROUP_ETGRID_GENERATOR", sequenceName = "ETHNICGROUP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ETHNICGROUP_ETGRID_GENERATOR")
    @Column(name = "ETGR_ID", unique = true, nullable = false, precision = 10)
    private long etgrId;

    @Column(name = "ETGR_DESCRIPTION", length = 255)
    private String etgrDescription;

    @Column(name = "ETGR_NAME", nullable = false, length = 255)
    private String etgrName;

    //bi-directional many-to-one association to Person
    @ManyToOne
    @JoinColumn(name = "PERSON_PERS_ID")
    @JsonIgnore
    private Person person;

    public Ethnicgroup() {
    }

    public long getEtgrId() {
        return this.etgrId;
    }

    public void setEtgrId(long etgrId) {
        this.etgrId = etgrId;
    }

    public String getEtgrDescription() {
        return this.etgrDescription;
    }

    public void setEtgrDescription(String etgrDescription) {
        this.etgrDescription = etgrDescription;
    }

    public String getEtgrName() {
        return this.etgrName;
    }

    public void setEtgrName(String etgrName) {
        this.etgrName = etgrName;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
