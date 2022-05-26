package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the PERSON database table.
 */
@Entity
@Table(name = "PERSON")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PERSON_PERSID_GENERATOR", allocationSize = 1, sequenceName = "PERSON_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_PERSID_GENERATOR")
    @Column(name = "PERS_ID", unique = true, nullable = false, precision = 10)
    private long persId;

    @Column(name = "PERS_ADDRESS", length = 255, nullable = true)
    private String persAddress;

    @Column(name = "PERS_DOCUMENT", length = 20, nullable = true)
    private String persDocument;

    @Column(name = "PERS_EMAIL", length = 255, unique = true)
    private String persEmail;

    @Column(name = "PERS_FIRST_NAME", length = 255, nullable = true)
    private String persFirstName;

    @Column(name = "PERS_GENRE", length = 4, nullable = true)
    private String persGenre;

    @Column(name = "PERS_LAST_NAME", length = 255, nullable = true)
    private String persLastName;

    //bi-directional many-to-one association to Curriculum
    @OneToMany(mappedBy = "person")
    //@JsonIgnore
    private List<Curriculum> curriculums;

    //bi-directional many-to-one association to Curriculum
    @ManyToOne
    @JoinColumn(name = "CURRICULUM_CURR_ID")
    @JsonIgnore
    private Curriculum curriculum;

    //bi-directional many-to-many association to Language
    @ManyToMany
    @JoinTable(
            name = "PERS_LANGUAGE"
            , joinColumns = {
            @JoinColumn(name = "PERSON_PERS_ID", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "LANGUAGE_LANGU_ID", nullable = false)
    }
    )
    //@JsonIgnore
    private List<Language> languages;


    @Column(name = "PERS_COUNTRY_NAME")
    private String persCountryName;

    @Column(name = "PERS_CITY_NAME")
    private String persCityName;

    //@OneToMany(mappedBy = "person")
    @ManyToMany
    @JoinTable(
            name = "PERS_CAREER"
            , joinColumns = {
            @JoinColumn(name = "PERSON_PERS_ID", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "CAREER_CARE_ID", nullable = false)
    }
    )
    //@JsonIgnore
    private List<Career> careers;


    public Person() {
    }

    public long getPersId() {
        return this.persId;
    }

    public void setPersId(long persId) {
        this.persId = persId;
    }

    public String getPersAddress() {
        return this.persAddress;
    }

    public void setPersAddress(String persAddress) {
        this.persAddress = persAddress;
    }

    public String getPersDocument() {
        return this.persDocument;
    }

    public void setPersDocument(String persDocument) {
        this.persDocument = persDocument;
    }

    public String getPersEmail() {
        return this.persEmail;
    }

    public void setPersEmail(String persEmail) {
        this.persEmail = persEmail;
    }

    public String getPersFirstName() {
        return this.persFirstName;
    }

    public void setPersFirstName(String persFirstName) {
        this.persFirstName = persFirstName;
    }

    public String getPersGenre() {
        return this.persGenre;
    }

    public void setPersGenre(String persGenre) {
        this.persGenre = persGenre;
    }

    public String getPersLastName() {
        return this.persLastName;
    }

    public void setPersLastName(String persLastName) {
        this.persLastName = persLastName;
    }

    public List<Curriculum> getCurriculums() {
        return this.curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public Curriculum addCurriculum(Curriculum curriculum) {
        getCurriculums().add(curriculum);
        curriculum.setPerson(this);

        return curriculum;
    }

    public Curriculum removeCurriculum(Curriculum curriculum) {
        getCurriculums().remove(curriculum);
        curriculum.setPerson(null);

        return curriculum;
    }

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }


    public Career addCareer(Career career) {
        getCareers().add(career);
        career.setPerson(this);
        return career;
    }

    public Career removeCareer(Career career) {
        getCareers().remove(career);
        career.setPerson(null);
        return career;
    }


    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getPersCountryName() {
        return persCountryName;
    }

    public void setPersCountryName(String persCountryName) {
        this.persCountryName = persCountryName;
    }

    public String getPersCityName() {
        return persCityName;
    }

    public void setPersCityName(String persCityName) {
        this.persCityName = persCityName;
    }




}
