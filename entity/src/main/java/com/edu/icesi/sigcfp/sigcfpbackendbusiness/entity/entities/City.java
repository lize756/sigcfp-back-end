package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the CITY database table.
 */
@Entity
@Table(name = "CITY")
@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CITY_CITYID_GENERATOR", sequenceName = "CITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_CITYID_GENERATOR")
    @Column(name = "CITY_ID", unique = true, nullable = false, precision = 10)
    private long cityId;

    @Column(name = "CITY_NAME", nullable = false, length = 255)
    private String cityName;

    //bi-directional many-to-one association to Academicstudy
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Academicstudy> academicstudies;

    //bi-directional many-to-one association to Company
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Company> companies;

    //bi-directional many-to-one association to Person
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Person> persons;

    public City() {
    }

    public long getCityId() {
        return this.cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Academicstudy> getAcademicstudies() {
        return this.academicstudies;
    }

    public void setAcademicstudies(List<Academicstudy> academicstudies) {
        this.academicstudies = academicstudies;
    }

    public Academicstudy addAcademicstudy(Academicstudy academicstudy) {
        getAcademicstudies().add(academicstudy);
        academicstudy.setCity(this);

        return academicstudy;
    }

    public Academicstudy removeAcademicstudy(Academicstudy academicstudy) {
        getAcademicstudies().remove(academicstudy);
        academicstudy.setCity(null);

        return academicstudy;
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Company addCompany(Company company) {
        getCompanies().add(company);
        company.setCity(this);

        return company;
    }

    public Company removeCompany(Company company) {
        getCompanies().remove(company);
        company.setCity(null);

        return company;
    }

    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Person addPerson(Person person) {
        getPersons().add(person);
        person.setCity(this);

        return person;
    }

    public Person removePerson(Person person) {
        getPersons().remove(person);
        person.setCity(null);

        return person;
    }

}