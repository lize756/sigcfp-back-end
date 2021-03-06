package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the "language" database table.
 */
@Entity
@Table(name = "language")
@NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "LANGUAGE_LANGUID_GENERATOR", allocationSize = 1, sequenceName = "language_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_LANGUID_GENERATOR")
    @Column(name = "LANGU_ID", unique = true, nullable = false, precision = 19)
    private long languId;

    @Column(name = "LANGU_LEVEL", nullable = false, length = 255)
    private String languLevel;

    @Column(name = "LANGU_NAME", nullable = false, length = 255)
    private String languName;

    //bi-directional many-to-one association to Person
    @ManyToOne
    @JoinColumn(name = "PERSON_PERS_ID")
    @JsonIgnore
    private Person person;

    public Language() {
    }

    public long getLanguId() {
        return this.languId;
    }

    public void setLanguId(long languId) {
        this.languId = languId;
    }

    public String getLanguLevel() {
        return this.languLevel;
    }

    public void setLanguLevel(String languLevel) {
        this.languLevel = languLevel;
    }

    public String getLanguName() {
        return this.languName;
    }

    public void setLanguName(String languName) {
        this.languName = languName;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
